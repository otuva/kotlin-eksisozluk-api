import com.exanple.*

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.datetime.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

val routes = mapOf<String,String>(
    "apiUrl" to "https://api.eksisozluk.com",
    "login" to "/Token",
    "anonLogin" to "/v2/account/anonymoustoken",
    "clientInfo" to "/v2/clientsettings/info",
    "time" to "/v2/clientsettings/time",
    "topic" to "/v2/topic/{}",
    "entry" to "/v2/entry/%s",
    "entryFavorite" to "/v2/entry/favorite",
    "entryUnfavorite" to "/v2/entry/unfavorite",
    "user" to "/v2/user/{}",
    "userFollow" to "/v2/user/follow",
    "userUnfollow" to "/v2/user/unfollow",
    "userBlock" to "/v2/user/block",
    "userUnblock" to "/v2/user/unblock",
    "userIndexTitlesBlock" to "/v2/user/indextitlesblock",
    "userRemoveIndexTitlesBlock" to "/v2/user/indextitlesblock",
    "user_entries" to "/v2/user/{}/entries",
    "userFavorited" to "/v2/user/{}/favorited",
    "userLastVoted" to "/v2/user/{}/lastvoted",
    "userLastWeekMostVoted" to "/v2/user/{}/lastweekmostvoted",
    "indexPopular" to "/v2/index/popular",
    "indexToday" to "/v2/index/today",
    "indexGetUserChannelFilters" to "/v2/index/getuserchannelfilters",
    "indexSetChannelFilter" to "/v2/index/setchannelfilter"
)

class EksiClient(_username: String?, _password: String?) {
    private lateinit var client: HttpClient

    val apiSecret: String = "68f779c5-4d39-411a-bd12-cbcc50dc83dd"
    val username: String? = _username
    val password: String? = _password
    val clientSecret = UUID.randomUUID()
    val clientUniqueId = UUID.randomUUID()
    lateinit var token: EksiToken

    suspend fun authorize() {
        val tempClient = HttpClient(CIO) {
            install(UserAgent) {
                agent = "okhttp/3.12.1"
            }
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                header("Content-Type", "application/x-www-form-urlencoded")
            }
        }
        if (username == null || password == null) {
            // anonymous login
            anonLogin(tempClient)
        } else {
            // login
            login(tempClient)
        }
        tempClient.close()

        client = HttpClient(CIO) {
            install(UserAgent) {
                agent = "okhttp/3.12.1"
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
            defaultRequest {
                headers.appendIfNameAbsent("Authorization", "Bearer ${token.accessToken}")
                headers.appendIfNameAbsent("Client-Secret", clientSecret.toString())
                headers.appendIfNameAbsent("Api-Secret", apiSecret)
            }
        }
    }

    suspend fun getEntry(entryId: Int) {
        val response = client.get(routes["apiUrl"] + routes["entry"]!!.format(entryId))
        println(response.bodyAsText())
    }

    private suspend fun anonLogin(client: HttpClient) {
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

        token = deserializeAnon(response.bodyAsText())
    }

    private suspend fun login(client: HttpClient) {
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
        token = deserialize(response.bodyAsText())
    }
}

suspend fun main() {
//    val currentMoment: Instant = Clock.System.now()
//    println(currentMoment)
//    println(currentMoment+ 14399.seconds)

//    println(Clock.System.now())



    val eksiClient1 = EksiClient(null, null)
    eksiClient1.authorize()
    eksiClient1.getEntry(1)


//
//
//    eksiClient.authorize()


//    println(eksiClient.token)
//    println(eksiClient1.token)
//    println(serialize(eksiClient1.token))

//    println(routes["entry"]!!.format(5))
//    println(routes["entry"]!!.format("t/est"))

//    println(routes["apiUrl"])
}