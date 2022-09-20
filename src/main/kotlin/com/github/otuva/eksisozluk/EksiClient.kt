// TODO
// change java uuid with cross platform one
// change logger from default to android

package com.github.otuva.eksisozluk

//import kotlinx.serialization.encodeToString
import com.github.otuva.eksisozluk.models.auth.EksiToken
import com.github.otuva.eksisozluk.models.auth.Session
import com.github.otuva.eksisozluk.models.entry.Entry
import com.github.otuva.eksisozluk.models.topic.Topic
import com.github.otuva.eksisozluk.models.user.User
import com.github.otuva.eksisozluk.models.user.entries.UserEntries
import com.github.otuva.eksisozluk.responses.AnonLoginResponse
import com.github.otuva.eksisozluk.responses.TopicResponse
import com.github.otuva.eksisozluk.responses.UserEntriesResponse
import com.github.otuva.eksisozluk.responses.UserResponse
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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

val routes = mapOf(
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
    "indexPopular" to "/v2/index/popular",
    "indexToday" to "/v2/index/today",
    "indexGetUserChannelFilters" to "/v2/index/getuserchannelfilters",
    "indexSetChannelFilter" to "/v2/index/setchannelfilter"
)

class EksiClient(val username: String? = null, val password: String? = null) {
    val apiSecret: String = "68f779c5-4d39-411a-bd12-cbcc50dc83dd"
    private lateinit var client: HttpClient
    lateinit var session: Session

    /**
     * Just echo function for debugging url responses. For testing not implemented endpoints.
     * */
    suspend fun getResponse(url: String) {
        println(client.get(url).bodyAsText())
    }

    /**
     * Creates actual client with set [session].
     * If session is not set, it calls [createSession] function.
     *
     * @return [Unit]
     * */
    suspend fun authorize() {
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

    suspend fun getEntry(entryId: Int): Entry {
        val response = client.get(routes["apiUrl"] + routes["entry"]!!.format(entryId))

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!.getFirstEntry()
    }

    suspend fun getTopic(topicId: Int, page: Int = 1): Topic {
        val response = client.get(routes["apiUrl"] + routes["topic"]!!.format(topicId) + "?p=$page")

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!
    }

    suspend fun getEntryAsTopic(entryId: Int): Topic {
        val response = client.get(routes["apiUrl"] + routes["entry"]!!.format(entryId))

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!
    }

    suspend fun getUser(username: String): User {
        val response = client.get(routes["apiUrl"] + routes["user"]!!.format(encodeSpaces(username)))

        val userResponse: UserResponse = response.body()

        return userResponse.data
    }

    suspend fun getUserEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userEntries"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    suspend fun getUserFavoriteEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userFavorites"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    suspend fun getUserMostFavoritedEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userFavorited"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    suspend fun getUserLastVotedEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userLastVoted"]!!.format(encodeSpaces(username)) + "?p=$page")

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    suspend fun getUserLastWeekMostVotedEntries(username: String, page: Int = 1): UserEntries? {
        val response =
            client.get(routes["apiUrl"] + routes["userLastWeekMostVoted"]!!.format(encodeSpaces(username)) + "?p=$page")
        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Login to EksiSozluk with current username and password [login] and initialize [session].
     * If username and password is not provided, it will try to log in with anonymous account [anonLogin].
     * Know that you can change [username] and [password] before calling this function.
     * [Session.clientSecret] and [Session.clientUniqueId] will be set here because they must be persistent throughout the session.
     * (i.e. Must validate the bearer token [Session.token])
     *
     * @return [Unit]
     *
     * @see [Session]
     * */
    suspend fun createSession() {
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
        if (username == null || password == null) {
            // anonymous login
            token = anonLogin(tempClient, clientSecret.toString(), clientUniqueId.toString())
        } else {
            // login
            token = login(tempClient, clientSecret.toString(), clientUniqueId.toString())
        }
        tempClient.close()
        session = Session(clientSecret, clientUniqueId, token)
    }

    /**
     * Send request to anonymous token endpoint.
     *
     * @param client temporary client to get token
     *
     * @return [EksiToken]
     * */
    private suspend fun anonLogin(client: HttpClient, clientSecret: String, clientUniqueId: String): EksiToken {
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
    private suspend fun login(client: HttpClient, clientSecret: String, clientUniqueId: String): EksiToken {
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

    private fun encodeSpaces(string: String): String {
        return string.replace(" ", "%20")
    }
}

@Suppress("unused")
suspend fun stfu() {
    val eksiClient = EksiClient()

    eksiClient.getResponse("https://eksisozluk.com/entry/1")
    eksiClient.getEntry(1)
    eksiClient.getTopic(1)
    eksiClient.getEntryAsTopic(1)
    eksiClient.getUserEntries("ssg")
    eksiClient.getUserFavoriteEntries("ssg")
    eksiClient.getUserMostFavoritedEntries("ssg")
    eksiClient.getUserLastVotedEntries("ssg")
    eksiClient.getUserLastWeekMostVotedEntries("ssg")
}

/**
 * temporary main function for testing
 * this will be removed in the future
 * */
suspend fun main() {
    val eksiClient = EksiClient()

    // write or read file in current folder
    val file = File("sozluk.session")
//     file.writeText(Json.encodeToString(eksiClient.session))
    val currentSession: Session = Json.decodeFromString(file.readText())

    eksiClient.session = currentSession

    eksiClient.authorize()

    println(eksiClient.getUser("divit"))

}