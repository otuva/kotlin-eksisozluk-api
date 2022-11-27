package com.github.otuva.eksisozluk.models.matter

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * All the information about a matter.
 *
 * @param matterTitle The title of the matter.
 * @param id The id of the matter.
 * @param topicId The id of the topic that the matter belongs to.
 * @param details Question body of the matter.
 * @param created The date and time that the matter was created.
 * @param lastUpdated The date and time that the matter was last updated.
 * @param page Single page of the matter. Includes all the answers and pagination information.
 * @param author The author of the matter.
 * @param hasDetails Whether the matter has a question body.
 * @param hidden Whether the matter is hidden.
 * @param active Whether the matter is active.
 * @param hasAnswers Whether the matter has answers or not.
 * @param allowsAnswers If the matter allows answers.
 * @param answerCount The total number of answers that the matter has.
 * @param upVote The number of up votes that the matter has. Always 0
 * @param downVote The number of down votes that the matter has. Always 0
 * @param rate The rate of the matter. Always 0
 * @param isTracked If user is following the matter.
 * @param slug The slug of the matter.
 * @param avatarUrl The url of the author's avatar.
 * */
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
