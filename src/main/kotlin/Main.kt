//import io.ktor.client.*
//import io.ktor.client.engine.cio.*
//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import io.ktor.client.plugins.*
//import io.ktor.client.plugins.auth.*
//import io.ktor.client.plugins.auth.providers.*
////import io.ktor.client.plugins.contentnegotiation.*
////import io.ktor.serialization.kotlinx.json.*
////import kotlinx.serialization.Serializable
//import java.util.UUID
//
//
////@Serializable
////data class Customer(val id: Int, val firstName: String, val lastName: String)
//
//// create object to hold
//val routes1 = object {
//    var apiUrl: String = "https://api.eksisozluk.com"
//    val login = "/Token"
//    val anonLogin = "/v2/account/anonymoustoken"
//    val clientInfo = "/v2/clientsettings/info"
//    val time = "/v2/clientsettings/time"
//    val topic = "/v2/topic/{}"
//    val entry = "/v2/entry/{}"
//    val entryFavorite = "/v2/entry/favorite"
//    val entryUnfavorite = "/v2/entry/unfavorite"
//    val user = "/v2/user/{}"
//    val userFollow = "/v2/user/follow"
//    val userUnfollow = "/v2/user/unfollow"
//    val userBlock = "/v2/user/block"
//    val userUnblock = "/v2/user/unblock"
//    val userIndexTitlesBlock = "/v2/user/indextitlesblock"
//    val userRemoveIndexTitlesBlock = "/v2/user/indextitlesblock"
//    val user_entries = "/v2/user/{}/entries"
//    val userFavorited = "/v2/user/{}/favorited"
//    val userLastVoted = "/v2/user/{}/lastvoted"
//    val userLastWeekMostVoted = "/v2/user/{}/lastweekmostvoted"
//    val indexPopular = "/v2/index/popular"
//    val indexToday = "/v2/index/today"
//    val indexGetUserChannelFilters = "/v2/index/getuserchannelfilters"
//    val indexSetChannelFilter = "/v2/index/setchannelfilter"
//}
//
//class EksiClient1(_username: String?, _password: String?) {
//    //    private val client: HttpClient
//    val apiSecret: String = "68f779c5-4d39-411a-bd12-cbcc50dc83dd"
//    val username: String? = _username
//    val password: String? = _password
//    val clientSecret = UUID.randomUUID()
//    val clientUniqueId = UUID.randomUUID()
//    var token: String? = null
//
//    init {
//        authorize()
////        anonLogin()
//    }
//
//    fun authorize() {
//        val tempClient = HttpClient(CIO) {
//            install(UserAgent) {
//                agent = "okhttp/3.12.1"
//            }
//            defaultRequest {
//                header("Content-Type", "application/x-www-form-urlencoded")
//            }
//        }
//
//        // user login
//        if (username != null && password != null) {
//            token = "login"
////            val response: HttpResponse = httpClient.post("https://eksisozluk.com/api/v1/authorize") {
////                body = "username=$username&password=$password&client_secret=$clientSecret&client_unique_id=$clientUniqueId"
////            }
////            token = response.headers["X-Auth-Token"]
//        }
//        // anonymous login
//        else {
//            val response = tempClient.post(routes.apiUrl + routes.anonLogin) {
//                setBody("Platform=g&Version=2.0.0&Build=51&Api-Secret=${apiSecret}&Client-Secret=${clientSecret}&ClientUniqueId=${clientUniqueId}")
//            }
//            println(response)
//
//            token = "anon-login"
//        }
//    }
//
//    fun login() {
//
//    }
//
////    suspend fun anonLogin() {
////        val url = "https://api.eksisozluk.com/v2/account/anonymoustoken"
////
////        val response = client.post(url) {
////            setBody("Platform=g&Version=2.0.0&Build=51&Api-Secret=${apiSecret}&Client-Secret=${clientSecret}&ClientUniqueId=${clientUniqueId}")
////            headers {
////                append("Content-Type", "application/x-www-form-urlencoded")
////            }
////        }
////
////        println(response.bodyAsText())
////    }
//
//
////    suspend fun getEntry(entryId: String): String {
////        val response = client.get<HttpResponse>("https://eksisozluk.com/$entryId")
////        return response.readText()
////    }
//}
//
////data class headers(val userAgent: UserAgent)
//
////suspend fun anonLogin() {
////    val client = HttpClient(CIO) {
////        install(UserAgent) {
////            agent = "okhttp/3.12.1"
////        }
////        install(Auth) {
////            bearer {
////                loadTokens { listOf("Bearer token") }
////            }
////        }
////    }
////
////    val apiSecret = "68f779c5-4d39-411a-bd12-cbcc50dc83dd"
////    val clientSecret = UUID.randomUUID()
////    val clientUniqueId = UUID.randomUUID()
////
////
////    val url = "https://api.eksisozluk.com/v2/account/anonymoustoken"
////
////    val response = client.post(url) {
////        setBody("Platform=g&Version=2.0.0&Build=51&Api-Secret=${apiSecret}&Client-Secret=${clientSecret}&ClientUniqueId=${clientUniqueId}")
////        headers {
////            append("Content-Type", "application/x-www-form-urlencoded")
////        }
////    }
////
////    println(response.bodyAsText())
////}
//
//fun main() {
////    anonLogin()
//}
////    val client = HttpClient(CIO)
////    val response: HttpResponse = client.get("https://ktor.io/")
////    println(response.status)
////    println(response.bodyAsText())
////    client.close()
