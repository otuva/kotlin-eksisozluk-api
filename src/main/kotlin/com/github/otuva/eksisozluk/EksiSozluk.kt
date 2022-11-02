package com.github.otuva.eksisozluk

import com.github.otuva.eksisozluk.models.authentication.Session
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.endpoints.client.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json

public class EksiSozluk(
    username: String? = null,
    password: String? = null,
    existingSession: Session? = null,
) {
    private val userType: UserType
    private val client: HttpClient
    public val session: Session

    public val entry: EntryApi
    public val user: UserApi
    public val topic: TopicApi
    public val index: IndexApi
    public val search: SearchApi
    public val message: MessageApi
    public val matter: MatterApi

    init {
        session = existingSession ?: Session(username, password)

        check(session.token.isExpired().not()) { throw TokenExpiredException("Token expired. Consider calling refreshToken() function.") }

        userType = if (session.token.nick == null) {
            UserType.Anonymous
        } else {
            UserType.Regular
        }

        client = HttpClient(CIO) {

            install(UserAgent) {
                agent = "okhttp/3.12.1"
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            // this check is necessary because there's no refresh token when the user is anonymous
            if (session.token.userId != null) {
                install(Auth) {
                    bearer {
                        loadTokens {
                            BearerTokens(session.token.accessToken, session.token.refreshToken!!)
                        }
                    }
                }
            }

            defaultRequest {
                headers.appendIfNameAbsent("Authorization", "Bearer ${session.token.accessToken}")
                headers.appendIfNameAbsent("Client-Secret", session.clientSecret.toString())
                headers.appendIfNameAbsent("Api-Secret", apiSecret)
            }

            // for exception handling
            expectSuccess = true
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    exceptionHandler(exception, request)
                }
            }

        }

        entry = EntryApi(client, userType)
        user = UserApi(client, userType)
        topic = TopicApi(client, userType)
        index = IndexApi(client, userType)
        search = SearchApi(client, userType)
        message = MessageApi(client, userType)
        matter = MatterApi(client, userType)
    }

    public companion object {
        public const val apiSecret: String = "68f779c5-4d39-411a-bd12-cbcc50dc83dd"

        /**
         * Used in functions that require a user to be logged in.
         * */
        @Throws(NotAuthorizedException::class)
        public fun requireUserLogin(userType: UserType) {
            require(userType == UserType.Regular) { throw NotAuthorizedException("Anonymous users cannot do this.") }
        }
    }
}