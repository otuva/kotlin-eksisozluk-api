package com.github.otuva.eksisozluk.models.message

import kotlinx.serialization.Serializable

/**
 * Used to send a message to a user.
 *
 * @param message Content of the message.
 * @param threadId Id of the thread, always 0.
 * @param to Username of the recipient.
 * */
@Serializable
public data class Send(
    val message: String,
    val threadId: Int,
    val to: String
)
