package io.github.otuva.eksisozluk

import io.github.otuva.eksisozluk.models.EksiToken
import io.github.otuva.eksisozluk.models.deserializeAuth
import io.github.otuva.eksisozluk.models.deserializeAnonAuth
import io.github.otuva.eksisozluk.models.deserializeEntry

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import java.util.UUID

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

        token = deserializeAnonAuth(response.bodyAsText())
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
        token = deserializeAuth(response.bodyAsText())
    }
}

suspend fun main() {
    // TODO:
    //  add logger
    val eksiClient = EksiClient(null, null)
    eksiClient.authorize()
    eksiClient.getEntry(132884409)

//    val myEntry = deserializeEntry("""{"Id":130169603,"Content":"the many saints of newark adli filmin protagonisti olan karakter.\r\n\r\nrichard \"dickie\" moltisanti soprano crew askerlerinden biri olup, christopher moltisantinin babasidir.","Author":{"Nick":"fulco","Id":2851178},"Created":"2021-11-17T14:32:07.907","LastUpdated":"2021-12-12T16:20:00","IsFavorite":false,"FavoriteCount":0,"Hidden":true,"Active":false,"CommentCount":0,"CommentSummary":null,"AvatarUrl":null,"Media":null,"IsSponsored":false,"IsPinned":false,"IsPinnedOnProfile":false,"IsVerifiedAccount":false}""")
//    println(myEntry)
//    val myMediaEntry = deserializeEntry("""{"Id":142331099,"Content":"tam aile kurulacak kadındır.\n\n\"işte çocuklarımın annesi olacak kişi buuuu!!\" diye haykırasım gelir;\n\n[https://cdn.eksisozluk.com/2022/9/6/p/pbrecem9.jpg?key=pbrecem9 görsel]","Author":{"Nick":"golgeleringucunegidipgelenadam","Id":2398682},"Created":"2022-09-06T22:03:15.087","LastUpdated":null,"IsFavorite":false,"FavoriteCount":3,"Hidden":false,"Active":true,"CommentCount":0,"CommentSummary":null,"AvatarUrl":"https://img.ekstat.com/profiles/golgeleringucunegidipgelenadam-637980141299896635.jpg","Media":["https://cdn.eksisozluk.com/2022/9/6/p/pbrecem9.jpg?key=pbrecem9"],"IsSponsored":false,"IsPinned":false,"IsPinnedOnProfile":false,"IsVerifiedAccount":false}""")
//    println(myMediaEntry)
//    val myMediaListEntry = deserializeEntry("""{"Id":141349303,"Content":"bir fotoğrafa, çizgi filmi efekti verilerek farklı anlamlar yüklenebilir, isimleri üzerine düşünmek ise bir hayli keyiflidir. \n\nbugün selamlaştığım ağaçlardan birkaçı;\n\niçinden göktaşı geçmiş bir arazi parçası;\n[https://cdn.eksisozluk.com/2022/8/15/v/vh9zduqq.jpg?key=vh9zduqq görsel]\n\nyılanların saldırısına uğramış bir ağaç kovuğu;\n[https://cdn.eksisozluk.com/2022/8/15/3/3losd1t4.jpg?key=3losd1t4 görsel]\n\nkozasında rüyaya dalmış bir göz bebeği;\n[https://cdn.eksisozluk.com/2022/8/15/z/z8ao510l.jpg?key=z8ao510l görsel]\n\nderisi kırışmış bir ağacın kalabalıklar içindeki yalnızlığı;\n[https://cdn.eksisozluk.com/2022/8/15/q/q5tihs4h.jpg?key=q5tihs4h görsel]","Author":{"Nick":"melody dream","Id":2420144},"Created":"2022-08-15T18:25:21.793","LastUpdated":null,"IsFavorite":false,"FavoriteCount":10,"Hidden":false,"Active":true,"CommentCount":0,"CommentSummary":null,"AvatarUrl":"https://img.ekstat.com/profiles/melody-dream-637971516292102127.jpg","Media":["https://cdn.eksisozluk.com/2022/8/15/v/vh9zduqq.jpg?key=vh9zduqq","https://cdn.eksisozluk.com/2022/8/15/3/3losd1t4.jpg?key=3losd1t4","https://cdn.eksisozluk.com/2022/8/15/z/z8ao510l.jpg?key=z8ao510l","https://cdn.eksisozluk.com/2022/8/15/q/q5tihs4h.jpg?key=q5tihs4h"],"IsSponsored":false,"IsPinned":false,"IsPinnedOnProfile":false,"IsVerifiedAccount":false}""")
//    println(myMediaListEntry)

}