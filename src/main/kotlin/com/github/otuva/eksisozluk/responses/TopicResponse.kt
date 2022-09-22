package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.BadTopicException
import com.github.otuva.eksisozluk.models.topic.Topic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Topic?
) {
    /**
     * This method is for entries that are not found. Because api returns 200 status code for not found entries.
     * */
    init {
        if (!success) throw BadTopicException(message!!)
    }
}