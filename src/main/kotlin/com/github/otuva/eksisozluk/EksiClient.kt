// TODO
// change java uuid with cross platform one
// change logger from default to android

package com.github.otuva.eksisozluk

import com.github.otuva.eksisozluk.models.auth.EksiToken
import com.github.otuva.eksisozluk.models.auth.Session
import com.github.otuva.eksisozluk.models.entry.Entry
import com.github.otuva.eksisozluk.models.index.Index
import com.github.otuva.eksisozluk.models.topic.Topic
import com.github.otuva.eksisozluk.models.user.User
import com.github.otuva.eksisozluk.models.user.entries.UserEntries
import com.github.otuva.eksisozluk.responses.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.UUID

public val routes: Map<String, String> = mapOf(
    "apiUrl" to "https://api.eksisozluk.com",
    "login" to "/Token",
    "anonLogin" to "/v2/account/anonymoustoken",
    "clientInfo" to "/v2/clientsettings/info",
    "time" to "/v2/clientsettings/time",
    "topic" to "/v2/topic/%s",
    "entry" to "/v2/entry/%s",
    "entryFavorite" to "/v2/entry/favorite",
    "entryUnfavorite" to "/v2/entry/unfavorite",
    "user" to "/v2/user/%s",
    "userFollow" to "/v2/user/follow",
    "userUnfollow" to "/v2/user/unfollow",
    "userBlock" to "/v2/user/block",
    "userUnblock" to "/v2/user/unblock",
    "userIndexTitlesBlock" to "/v2/user/indextitlesblock",
    "userRemoveIndexTitlesBlock" to "/v2/user/indextitlesblock",
    "userEntries" to "/v2/user/%s/entries",
    "userFavorited" to "/v2/user/%s/favorited",
    "userFavorites" to "/v2/user/%s/favorites",
    "userLastVoted" to "/v2/user/%s/lastvoted",
    "userLastWeekMostVoted" to "/v2/user/%s/lastweekmostvoted",
    "userSelfFavorited" to "/v2/user/%/selffavorited",
    "userBestEntries" to "/v2/user/%s/bestentries",
    "indexPopular" to "/v2/index/popular",
    "indexToday" to "/v2/index/today",
    "indexGetFilterChannels" to "/v2/index/getuserchannelfilters",
    "indexSetChannelFilter" to "/v2/index/setchannelfilter"
)

public class EksiClient(
    private val username: String? = null,
    private val password: String? = null
) {
    private val apiSecret: String = "68f779c5-4d39-411a-bd12-cbcc50dc83dd"
    private lateinit var client: HttpClient
    public lateinit var session: Session

    /*
     * ---------------------------------------------------------------------------------
     * ---------------------------debugging-(will-be-removed)---------------------------
     * ---------------------------------------------------------------------------------
     */
    public suspend fun debugGetResponse(url: String, method: String) {
        if (method == "GET") {
            println(client.get(url).bodyAsText())
        } else if (method == "POST") {
            println(client.post(url).bodyAsText())
        }
    }

    public suspend fun debugUseSessionFromFile() {
        val file = File("sozluk.session")
        if (file.exists()) {
            session = Json.decodeFromString(file.readText())

            if (session.token.expiresAt < Clock.System.now()) {
                refreshToken()
                file.writeText(Json.encodeToString(session))
            }

            buildClient()
        }
        else {
            println("Session file not found.")
        }
    }

    /*
    * ------------------------------------------------------------------------
    * ------------------------eksisozluk-api-functions------------------------
    * ------------------------------------------------------------------------
    * */

    public suspend fun getEntry(entryId: Int): Entry {
        val response = client.get(routes["apiUrl"] + routes["entry"]!!.format(entryId))

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!.getFirstEntry()
    }

    public suspend fun getTopic(topicId: Int, page: Int = 1): Topic {
        val response = client.get(routes["apiUrl"] + routes["topic"]!!.format(topicId) + "?p=$page")

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!
    }

    public suspend fun getEntryAsTopic(entryId: Int): Topic {
        val response = client.get(routes["apiUrl"] + routes["entry"]!!.format(entryId))

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!
    }

    public suspend fun getUser(username: String): User {
        val response = client.get(routes["apiUrl"] + routes["user"]!!.format(encodeSpaces(username)))

        val userResponse: UserResponse = response.body()

        return userResponse.data
    }

    public suspend fun getUserEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userEntries"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    public suspend fun getUserFavoriteEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userFavorites"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    public suspend fun getUserMostFavoritedEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userFavorited"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    public suspend fun getUserLastVotedEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userLastVoted"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    public suspend fun getUserLastWeekMostVotedEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userLastWeekMostVoted"]!!.format(encodeSpaces(username)) + "?p=$page")
        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Returns [UserEntries] object with entries that are written and favorited by the user. Aka "el emeği göz nuru".
     *
     * @param username Username of the user.
    */
    public suspend fun getUserSelfFavoritedEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userSelfFavorited"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    public suspend fun getUserBestEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userBestEntries"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    public suspend fun userFollow(username: String): GenericResponse {
        val url = routes["apiUrl"] + routes["userFollow"]!!.format(encodeSpaces(username))

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("nick", username)
                    }
                )
            )
        }

        return response.body()
    }

    public suspend fun userUnfollow(username: String): GenericResponse {
        val url = routes["apiUrl"] + routes["userUnfollow"]!!.format(encodeSpaces(username))

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("nick", username)
                    }
                )
            )
        }

        return response.body()
    }

    public suspend fun userBlock(username: String): GenericResponse {
        val url = routes["apiUrl"] + routes["userBlock"]!!.format(encodeSpaces(username))

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("nick", username)
                    }
                )
            )
        }

        return response.body()
    }

    public suspend fun userUnblock(username: String): GenericResponse {
        val url = routes["apiUrl"] + routes["userUnblock"]!!.format(encodeSpaces(username))

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("nick", username)
                    }
                )
            )
        }

        return response.body()
    }

    public suspend fun getIndexToday(page: Int = 1): Index {
        val response = client.get(routes["apiUrl"] + routes["indexToday"]!! + "?p=$page")

        val indexResponse: IndexResponse = response.body()

        return indexResponse.data
    }

    /*
    * ------------------------------------------------
    * ----------------client-functions----------------
    * ------------------------------------------------
    */

    /**
     * Creates actual client with set [session].
     * If session is not set, it calls [createSession] function.
     *
     * @return [Unit]
     * */
    public suspend fun buildClient() {
        if (!this::session.isInitialized) {
            createSession()
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
            expectSuccess = true
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    exceptionHandler(exception, request)
                }
            }
        }
    }

    public suspend fun refreshToken(): EksiToken {
        val token: EksiToken
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
        token = if (session.token.refreshToken == null) {
            // anon refresh
            requestAnonToken(tempClient, session.clientSecret.toString(), session.clientUniqueId.toString())
        }
        else {
            // user refresh
            refreshUserToken(tempClient, session.clientSecret.toString(), session.clientUniqueId.toString())
        }
        tempClient.close()
        session.token = token
        return token
    }

    /**
     * Login to EksiSozluk with current username and password ([requestUserToken] function) and initialize [session] and return initialized [session] object.
     * If username and password is not provided, it will try to log in with an anonymous account ([requestAnonToken] function).
     * Know that you can change [username] and [password] before calling this function.
     * [Session.clientSecret] and [Session.clientUniqueId] will be set here because they must be persistent throughout the session.
     * (i.e. Must validate the bearer token [Session.token])
     *
     * @return [Session] object
     * */
    public suspend fun createSession(): Session {
        val clientSecret = UUID.randomUUID()
        val clientUniqueId = UUID.randomUUID()
        val token: EksiToken

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
        token = if (username == null || password == null) {
            // anonymous login
            requestAnonToken(tempClient, clientSecret.toString(), clientUniqueId.toString())
        } else {
            // login
            requestUserToken(tempClient, clientSecret.toString(), clientUniqueId.toString())
        }
        tempClient.close()
        session = Session(clientSecret, clientUniqueId, token)
        return session
    }

    /**
     * Send request to anonymous token endpoint.
     *
     * @param client temporary client to get token
     *
     * @return [EksiToken]
     * */
    private suspend fun requestAnonToken(client: HttpClient, clientSecret: String, clientUniqueId: String): EksiToken {
        val url = routes["apiUrl"] + routes["anonLogin"]

        val anonLoginResponse: AnonLoginResponse = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("Platform", "g")
                        append("Version", "2.0.1")
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
     * Send request to user login endpoint
     *
     * @param client temporary client to get token
     *
     * @return [EksiToken]
     * */
    private suspend fun requestUserToken(client: HttpClient, clientSecret: String, clientUniqueId: String): EksiToken {
        val url = routes["apiUrl"] + routes["login"]

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("password", password!!)
                        append("Platform", "g")
                        append("Version", "2.0.1")
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

    private suspend fun refreshUserToken(client: HttpClient, clientSecret: String, clientUniqueId: String): EksiToken {
        val url = routes["apiUrl"] + routes["login"]

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("refresh_token", session.token.refreshToken!!)
                        append("Platform", "g")
                        append("Version", "2.0.1")
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

    private fun encodeSpaces(string: String): String {
        return string.replace(" ", "%20")
    }
}

/**
 * temporary main function for testing
 * this will be removed in the future
 * */
public suspend fun main() {
    val eksiClient = EksiClient()

    eksiClient.debugUseSessionFromFile()

    val testing = eksiClient.debugGetResponse(routes["apiUrl"] + routes["indexPopular"]!! + "?p=1", "POST")

    println(testing)
}