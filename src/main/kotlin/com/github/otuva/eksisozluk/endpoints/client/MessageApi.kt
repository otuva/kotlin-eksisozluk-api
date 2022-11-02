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

/**
 * Includes endpoints related to messages.
 * */
@RequiresLogin
public class MessageApi(private val client: HttpClient, private val userType: UserType) {

    /**
     * Requires login
     *
     * Get all inbox messages for the user
     *
     * @param page Page number
     *
     * @return [Messages] instance
     * */
    @RequiresLogin
    public suspend fun messages(page: Int = 1): Messages {
        EksiSozluk.requireUserLogin(userType)

        val url = Routes.api + Routes.Message.messages + "?p=$page"

        val response = client.get(url)

        val messagesResponse: MessagesResponse = response.body()

        return messagesResponse.data
    }

    /**
     * Requires login
     *
     * Get the thread for given user
     *
     * @param username Username to get messages
     * @param page Page number
     *
     * @return [Thread] object
     * */
    @RequiresLogin
    public suspend fun thread(username: String, page: Int = 1): Thread {
        EksiSozluk.requireUserLogin(userType)

        val url = Routes.api + Routes.Message.thread.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val threadResponse: ThreadResponse = response.body()

        return threadResponse.data
    }

    /**
     * Sends a message to given user
     *
     * @param username Username to send message
     * @param message Message to send
     *
     * @return Null if message could not be sent, otherwise [SentData] object containing sent message data such as id
     * */
    @RequiresLogin
    public suspend fun send(username: String, message: String): SentData? {
        EksiSozluk.requireUserLogin(userType)

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