package com.github.otuva.eksisozluk.responses.message

import com.github.otuva.eksisozluk.models.message.Messages
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MessagesResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Messages
)
