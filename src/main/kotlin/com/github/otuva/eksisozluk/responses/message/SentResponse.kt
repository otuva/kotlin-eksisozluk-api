package com.github.otuva.eksisozluk.responses.message

import com.github.otuva.eksisozluk.models.message.SentData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SentResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: SentData?
)
