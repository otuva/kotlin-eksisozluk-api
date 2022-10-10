package com.github.otuva.eksisozluk.models.message

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single message thread in inbox.
 *
 *
 * */
@Serializable
public data class Message(
    @SerialName("Id") val id: Int,
    @SerialName("User") val user: UserIdentifier,
    @SerialName("RawNick") val rawNick: String,
    @SerialName("IsArchive") val isArchive: Boolean,
    @SerialName("MaxMessageId") val maxMessageId: Int,
    @SerialName("Unread") val unread: Boolean,
    @SerialName("UnreadCount") val unreadCount: Int,
    @SerialName("Count") val count: Int,
    @SerialName("LastUpdate") val lastUpdate: LocalDateTime,
    @SerialName("Summary") val summary: String,
    @SerialName("LastUpdateFormatted") val lastUpdateFormatted: String,
    @SerialName("AvatarUrl") val avatarUrl: String?
)
