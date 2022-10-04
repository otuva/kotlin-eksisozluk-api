package com.github.otuva.eksisozluk.models.matter

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MatterItem(
    @SerialName("MatterId") val matterId: Int,
    @SerialName("MatterTitle") val matterTitle: String,
    @SerialName("Content") val content: String,
    @SerialName("Created") val created: LocalDateTime,
    @SerialName("LastUpdated") val lastUpdated: LocalDateTime?,
    @SerialName("Id") val id: Int,
    @SerialName("Author") val author: UserIdentifier,
    @SerialName("UpVote") val upVote: Int,
    @SerialName("DownVote") val downVote: Int,
    @SerialName("Matter") val matter: String?,
    @SerialName("Hidden") val hidden: Boolean,
    @SerialName("Active") val active: Boolean,
    @SerialName("Slug") val slug: String,
    @SerialName("AvatarUrl") val avatarUrl: String?,
)
