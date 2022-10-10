package com.github.otuva.eksisozluk.responses.message

import com.github.otuva.eksisozluk.models.message.thread.Thread
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ThreadResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Thread
)
