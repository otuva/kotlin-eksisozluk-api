package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.BadTopicException
import com.github.otuva.eksisozluk.models.Topic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Topic?
) {
    init {
        if (!success) throw BadTopicException(message!!)
    }
}