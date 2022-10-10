package com.github.otuva.eksisozluk.models.message.thread

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ThreadMessage(
    @SerialName("Id") val id: Int,
    @SerialName("IsOutgoing") val isOutgoing: Boolean,
    @SerialName("Date") val date: LocalDateTime,
    @SerialName("Content") val content: String,
)
