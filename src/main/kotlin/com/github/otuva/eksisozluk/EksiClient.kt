// TODO
// change java uuid with cross platform one
// change logger from default to android
//
// Implement:
// entry throwlamak yerine null dondursun
// genel arama
// base64 encoding ekle? belki
// entry favorileyenler listesi / caylak listesi
// follow unfollow topic
// kullanici basliklarini engelleme / kaldirma
// sorunsallar
// takip edilen kisilerin entry/fav
// tarihte bugun
// user sorunsali
// user sorunsal yaniti
// sorunsal index
// mesajlasma

// doc:
// ~~gundem anonim ve kayitli icin farkli calisiyor? ona bak~~
// ~~user image~~

package com.github.otuva.eksisozluk

import com.github.otuva.eksisozluk.models.authentication.Session
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.endpoints.client.*
import com.github.otuva.eksisozluk.utils.apiSecret
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

public class EksiClient(
    username: String? = null,
    password: String? = null,
    existingSession: Session? = null,
) {
    private var userType: UserType
    private var client: HttpClient
    public var session: Session

    public var entry: EntryApi
    public var user: UserApi
    public var topic: TopicApi
    public var index: IndexApi

    /**
     * will be removed.
     * */
    public suspend fun debugGetResponse(url: String, method: String) {
        if (method == "GET") {
            println(client.get(url).bodyAsText())
        } else if (method == "POST") {
            println(client.post(url).bodyAsText())
        }
    }

    init {
        session = existingSession ?: Session(username, password)

        check(session.token.expiresAt > Clock.System.now()) { TokenExpiredException("Token expired. Consider calling refreshToken() function.") }

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

        entry = EntryApi(client, userType)
        user = UserApi(client, userType)
        topic = TopicApi(client, userType)
        index = IndexApi(client, userType)
    }
}