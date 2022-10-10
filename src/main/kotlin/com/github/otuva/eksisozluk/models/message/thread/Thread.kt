package com.github.otuva.eksisozluk.models.message.thread

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Thread(
    @SerialName("ThreadId") val threadId: Int,
    @SerialName("IsArchive") val isArchive: Boolean,
    @SerialName("User") val user: UserIdentifier,
    @SerialName("IsSystem") val isSystem: Boolean,
    @SerialName("Messages") val messages: ThreadMessages,
    @SerialName("LastRead") val lastRead: Int,
    @SerialName("HasUnread") val hasUnread: Boolean,
    @SerialName("AvatarUrl") val avatarUrl: String?
)
