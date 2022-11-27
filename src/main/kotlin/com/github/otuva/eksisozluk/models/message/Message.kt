package com.github.otuva.eksisozluk.models.message

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a conversation between two users.
 *
 * @param id Conversation ID.
 * @param user User who is the other party in the conversation.
 * @param isArchived Whether the conversation is archived.
 * @param maxMessageId Last message ID in the conversation.
 * @param unread Whether the conversation is unread.
 * @param unreadCount Number of unread messages in the conversation.
 * @param count Number of messages in the conversation.
 * @param lastUpdate Last message's date and time.
 * @param summary Last message's content.
 * @param lastUpdateFormatted Last message's date and time in a human-readable format.
 * @param avatarUrl Avatar URL of the other party.
 * */
@Serializable
public data class Message(
    @SerialName("Id") val id: Int,
    @SerialName("User") val user: UserIdentifier,
    @SerialName("RawNick") val rawNick: String,
    @SerialName("IsArchive") val isArchived: Boolean,
    @SerialName("MaxMessageId") val maxMessageId: Int,
    @SerialName("Unread") val unread: Boolean,
    @SerialName("UnreadCount") val unreadCount: Int,
    @SerialName("Count") val count: Int,
    @SerialName("LastUpdate") val lastUpdate: LocalDateTime,
    @SerialName("Summary") val summary: String,
    @SerialName("LastUpdateFormatted") val lastUpdateFormatted: String,
    @SerialName("AvatarUrl") val avatarUrl: String?
)
