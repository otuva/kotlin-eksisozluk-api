package com.github.otuva.eksisozluk.models.authentication

import com.github.otuva.eksisozluk.annotations.ModifiesInternal
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.responses.AnonLoginResponse
import com.github.otuva.eksisozluk.utils.apiSecret
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.*

/**
 * Represents a EksiSozluk session which includes client variables and token pair.
 * This is just to make authorization things easier
 * [clientSecret] and [clientUniqueId] must be persistent throughout the session.
 * (i.e. Must validate the bearer [token]
 *
 * @param username Username to login
 * @param password Password to login
 * @param clientSecret random UUID
 * @param clientUniqueId random UUID
 * @property token instance of [EksiToken] that can be validated by uuids
 * */
@Serializable
public class Session(
    private val username: String?,
    private val password: String?,
    @Serializable(with = UUIDSerializer::class) public val clientSecret: UUID = UUID.randomUUID(),
    @Serializable(with = UUIDSerializer::class) public val clientUniqueId: UUID = UUID.randomUUID(),
) {
    public lateinit var token: EksiToken

    /**
     * Builds a new session with given username and password.
     * If username and password is not provided, it will try to log in with an anonymous account.
     *
     * clientSecret and clientUniqueId must be persistent throughout the session.
     * (i.e. Must validate the bearer token)
     * */
    init {
        if (!this::token.isInitialized) {
            val tempClient = HttpClient(CIO) {
                install(UserAgent) {
                    agent = "okhttp/3.12.1"
                }
                defaultRequest {
                    header("Content-Type", "application/x-www-form-urlencoded")
                }
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.INFO
                }
            }

            runBlocking {
                token = if (username.isNullOrBlank() || password.isNullOrBlank()) {
                    // anonymous login
                    requestAnonToken(tempClient, clientSecret.toString(), clientUniqueId.toString())
                } else {
                    // login
                    requestUserToken(tempClient, clientSecret.toString(), clientUniqueId.toString())
                }
            }

            tempClient.close()
        }
    }

    /**
     * Refreshes the bearer token.
     * This function will modify this object with the new [EksiToken] instance and return that too.
     *
     * @return [EksiToken]
     * */
    @ModifiesInternal
    public suspend fun refreshToken(): EksiToken {
        val newToken: EksiToken
        val tempClient = HttpClient(CIO) {
            install(UserAgent) {
                agent = "okhttp/3.12.1"
            }
            defaultRequest {
                header("Content-Type", "application/x-www-form-urlencoded")
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
        // refresh and assing new token
        newToken = if (token.refreshToken == null) {
            // anon refresh
            requestAnonToken(tempClient, clientSecret.toString(), clientUniqueId.toString())
        } else {
            // user refresh
            refreshUserToken(tempClient, clientSecret.toString(), clientUniqueId.toString())
        }
        tempClient.close()
        token = newToken
        return newToken
    }

    /**
     * Send request to anonymous token endpoint.
     * Note that requesting or refreshing tokens will not change anything in this object
     * and these functions will only return respective tokens.
     *
     * @param client temporary client to get token
     * @param clientSecret client secret
     * @param clientUniqueId client unique id
     *
     * @return [EksiToken]
     * */
    private suspend fun requestAnonToken(client: HttpClient, clientSecret: String, clientUniqueId: String): EksiToken {
        val url = Routes.baseUrl + Routes.Authentication.anonToken

        val anonLoginResponse: AnonLoginResponse = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("Platform", "g")
                        append("Version", "2.0.4")
                        append("Build", "52")
                        append("Api-Secret", apiSecret)
                        append("Client-Secret", clientSecret)
                        append("ClientUniqueId", clientUniqueId)
                    }
                )
            )
        }.body()

        return anonLoginResponse.data
    }

    /**
     * Refreshes expired tokens. It just returns the new token without touching this object.
     *
     * @param client temporary client to get token
     * @param clientSecret client secret
     * @param clientUniqueId client unique id
     *
     * @return [EksiToken]
     * */
    private suspend fun refreshUserToken(client: HttpClient, clientSecret: String, clientUniqueId: String): EksiToken {
        val url = Routes.baseUrl + Routes.Authentication.userToken

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("refresh_token", token.refreshToken!!)
                        append("Platform", "g")
                        append("Version", "2.0.4")
                        append("grant_type", "refresh_token")
                        append("Build", "52")
                        append("Api-Secret", apiSecret)
                        append("Client-Secret", clientSecret)
                        append("ClientUniqueId", clientUniqueId)
                    }
                )
            )
        }

        return response.body()
    }

    /**
     * Send request to user login endpoint.
     *
     * @param client temporary client to get token
     *
     * @return [EksiToken]
     * */
    private suspend fun requestUserToken(client: HttpClient, clientSecret: String, clientUniqueId: String): EksiToken {
        val url = Routes.baseUrl + Routes.Authentication.userToken

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("password", password!!)
                        append("Platform", "g")
                        append("Version", "2.0.4")
                        append("grant_type", "password")
                        append("Build", "52")
                        append("Api-Secret", apiSecret)
                        append("Client-Secret", clientSecret)
                        append("ClientUniqueId", clientUniqueId)
                        append("username", username!!)
                    }
                )
            )
        }

        return response.body()
    }
}