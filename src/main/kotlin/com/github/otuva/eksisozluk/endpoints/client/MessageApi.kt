package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.EksiSozluk
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.message.Messages
import com.github.otuva.eksisozluk.models.message.Send
import com.github.otuva.eksisozluk.models.message.SentData
import com.github.otuva.eksisozluk.models.message.thread.Thread
import com.github.otuva.eksisozluk.responses.message.MessagesResponse
import com.github.otuva.eksisozluk.responses.message.SentResponse
import com.github.otuva.eksisozluk.responses.message.ThreadResponse
import com.github.otuva.eksisozluk.utils.urlEncode
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

@RequiresLogin
public class MessageApi(private val client: HttpClient, private val userType: UserType) {

    @RequiresLogin
    public suspend fun messages(page: Int = 1): Messages {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.Message.messages + "?p=$page"

        val response = client.get(url)

        val messagesResponse: MessagesResponse = response.body()

        return messagesResponse.data
    }

    @RequiresLogin
    public suspend fun thread(username: String, page: Int = 1): Thread {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.Message.thread.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val threadResponse: ThreadResponse = response.body()

        return threadResponse.data
    }

    @RequiresLogin
    public suspend fun send(username: String, message: String): SentData? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.Message.send

        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(
                Send(
                    message,
                    0,
                    username
                )
            )
        }

        val sentResponse: SentResponse = response.body()

        return sentResponse.data
    }
}