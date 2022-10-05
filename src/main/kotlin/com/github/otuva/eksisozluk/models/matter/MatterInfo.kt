package com.github.otuva.eksisozluk.models.matter

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MatterInfo(
    @SerialName("MatterTitle") val matterTitle: String,
    @SerialName("Id") val id: Int,
    @SerialName("TopicId") val topicId: Int,
    @SerialName("Details") val details: String?,
    @SerialName("Created") val created: LocalDateTime,
    @SerialName("LastUpdated") val lastUpdated: LocalDateTime?,
    @SerialName("Page") val page: Page?,
    @SerialName("Author") val author: UserIdentifier,
    @SerialName("HasDetails") val hasDetails: Boolean,
    @SerialName("Hidden") val hidden: Boolean,
    @SerialName("Active") val active: Boolean,
    @SerialName("HasAnswers") val hasAnswers: Boolean,
    @SerialName("AllowsAnswers") val allowsAnswers: Boolean,
    @SerialName("AnswerCount") val answerCount: Int,
    @SerialName("UpVote") val upVote: Int,
    @SerialName("DownVote") val downVote: Int,
    @SerialName("Rate") val rate: Int,
    @SerialName("IsTracked") val isTracked: Boolean,
    @SerialName("Slug") val slug: String,
    @SerialName("AvatarUrl") val avatarUrl: String?,
)
