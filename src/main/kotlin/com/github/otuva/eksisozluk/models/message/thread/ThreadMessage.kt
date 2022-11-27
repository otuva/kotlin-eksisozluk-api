package com.github.otuva.eksisozluk.models.message.thread

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single message in a thread.
 *
 * @param id The id of the message.
 * @param isOutgoing Whether the message is sent by the user or not.
 * @param date The date of the message.
 * @param content The content of the message.
 * */
@Serializable
public data class ThreadMessage(
    @SerialName("Id") val id: Int,
    @SerialName("IsOutgoing") val isOutgoing: Boolean,
    @SerialName("Date") val date: LocalDateTime,
    @SerialName("Content") val content: String,
)
