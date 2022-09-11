// TODO
// change java uuid with cross platform one
// change logger from default to android

package com.github.otuva.eksisozluk

import com.github.otuva.eksisozluk.models.*
import com.github.otuva.eksisozluk.responses.deserializeAnonLoginResponse
import com.github.otuva.eksisozluk.responses.deserializeTopicResponse
import com.github.otuva.eksisozluk.responses.deserializeUserEntriesResponse
import com.github.otuva.eksisozluk.responses.deserializeUserResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.logging.*
import io.ktor.util.*
import java.util.UUID

val routes = mapOf<String,String>(
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
    "userLastVoted" to "/v2/user/%s/lastvoted",
    "userLastWeekMostVoted" to "/v2/user/%s/lastweekmostvoted",
    "indexPopular" to "/v2/index/popular",
    "indexToday" to "/v2/index/today",
    "indexGetUserChannelFilters" to "/v2/index/getuserchannelfilters",
    "indexSetChannelFilter" to "/v2/index/setchannelfilter"
)

class EksiClient(_username: String? = null, _password: String? = null) {
    private lateinit var client: HttpClient
    val apiSecret: String = "68f779c5-4d39-411a-bd12-cbcc50dc83dd"
    val username: String? = _username
    val password: String? = _password
    val clientSecret = UUID.randomUUID()
    val clientUniqueId = UUID.randomUUID()
    lateinit var token: EksiToken

    suspend fun _getResponse(url:String) {
        /*
        * Unsafe function for debugging url responses.
        * Cuz I couldn't find the problem with debugger lmao*/
        println(client.get(url).bodyAsText())
    }

    suspend fun authorize() {
        val tempClient = HttpClient(CIO) {
            install(UserAgent) {
                agent = "okhttp/3.12.1"
            }
            defaultRequest {
                header("Content-Type", "application/x-www-form-urlencoded")
            }
        }
        if (username == null || password == null) {
            // anonymous login
            token = anonLogin(tempClient)
        } else {
            // login
            token = login(tempClient)
        }
        tempClient.close()

        client = HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
            if (token.userId != null) {
                install(Auth) {
                    bearer {
                        loadTokens {
                            BearerTokens(token.accessToken, token.refreshToken!!)
                        }
                    }
                }
            }
            install(UserAgent) {
                agent = "okhttp/3.12.1"
            }
            defaultRequest {
                headers.appendIfNameAbsent("Authorization", "Bearer ${token.accessToken}")
                headers.appendIfNameAbsent("Client-Secret", clientSecret.toString())
                headers.appendIfNameAbsent("Api-Secret", apiSecret)
            }
        }
    }

    suspend fun getEntry(entryId: Int): Entry {
        val response = client.get(routes["apiUrl"] + routes["entry"]!!.format(entryId))
        val topic = deserializeTopicResponse(response.bodyAsText()).data
        return topic.getFirstEntry()
    }
    
    suspend fun getTopic(topicId: Int, page: Int = 1): Topic {
        val response = client.get(routes["apiUrl"] + routes["topic"]!!.format(topicId) + "?p=$page")
        val topic = deserializeTopicResponse(response.bodyAsText()).data
        return topic
    }

    suspend fun getEntryAsTopic(entryId: Int): Topic {
        val response = client.get(routes["apiUrl"] + routes["entry"]!!.format(entryId))
        val topic = deserializeTopicResponse(response.bodyAsText()).data
        return topic
    }

    suspend fun getUser(username: String): User {
        val response = client.get(routes["apiUrl"] + routes["user"]!!.format(username))
        return deserializeUserResponse(response.bodyAsText()).data
    }

    suspend fun getUserEntries(username: String, page: Int = 1): UserEntries {
        val response = client.get(routes["apiUrl"] + routes["userEntries"]!!.format(username) + "?p=$page")
        return deserializeUserEntriesResponse(response.bodyAsText()).data
    }

    private suspend fun anonLogin(client: HttpClient): EksiToken {
        val url = routes["apiUrl"] + routes["anonLogin"]
        val response: HttpResponse = client.post(url) {
            setBody("Platform=g&" +
                    "Version=2.0.0&" +
                    "Build=51&" +
                    "Api-Secret=${apiSecret}&" +
                    "Client-Secret=${clientSecret}&" +
                    "ClientUniqueId=${clientUniqueId}"
            )
        }

        return deserializeAnonLoginResponse(response.bodyAsText()).data
    }

    private suspend fun login(client: HttpClient): EksiToken {
        val url = routes["apiUrl"] + routes["login"]
        val response: HttpResponse = client.post(url) {
            setBody("password=${password}&" +
                    "Platform=g&" +
                    "Version=2.0.0&" +
                    "grant_type=password&" +
                    "Build=51&" +
                    "Api-Secret=${apiSecret}&" +
                    "Client-Secret=${clientSecret}&" +
                    "ClientUniqueId=${clientUniqueId}&" +
                    "username=${username}"
            )
        }
        return deserializeAuth(response.bodyAsText())
    }
}

suspend fun main() {
    val eksiClient = EksiClient()

    eksiClient.authorize()

    val user = eksiClient.getUserEntries("ssg", 1)
    val user1 = eksiClient.getUserEntries("ssg", 2)
    val user2 = eksiClient.getUserEntries("ssg", 3)

    println(user)
    println(user1)
    println(user2)
}