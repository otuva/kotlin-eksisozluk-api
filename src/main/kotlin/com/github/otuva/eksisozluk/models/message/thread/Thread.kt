package com.github.otuva.eksisozluk.models.message.thread

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a message thread with a user.
 *
 * @param threadId The id of the thread.
 * @param isArchived Whether the thread is archived or not.
 * @param user The user with whom the thread is.
 * @param isSystem If the thread is with the system.
 * @param messages The messages in the thread.
 * @param hasUnread Whether the thread has unread messages or not.
 * @param avatarUrl The url of the avatar of the user.
 * */
@Serializable
public data class Thread(
    @SerialName("ThreadId") val threadId: Int,
    @SerialName("IsArchive") val isArchived: Boolean,
    @SerialName("User") val user: UserIdentifier,
    @SerialName("IsSystem") val isSystem: Boolean,
    @SerialName("Messages") val messages: ThreadMessages,
    @SerialName("LastRead") val lastRead: Int,
    @SerialName("HasUnread") val hasUnread: Boolean,
    @SerialName("AvatarUrl") val avatarUrl: String?
)
