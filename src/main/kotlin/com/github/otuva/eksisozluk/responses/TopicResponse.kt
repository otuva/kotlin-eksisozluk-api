package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.BadTopicException
import com.github.otuva.eksisozluk.models.topic.Topic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TopicResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Topic?
) {
    /**
     * This constructor conditional is for entries that are not found. Because api returns 200 status code for not found entries.
     * */
    init {
        require(success) { throw BadTopicException(message!!) }
    }
}