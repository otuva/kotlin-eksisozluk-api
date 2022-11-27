package com.github.otuva.eksisozluk.models.matter

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single answer of a matter.
 *
 * @param matterId The id of the matter that the answer belongs to.
 * @param matterTitle The title of the matter
 * @param content Body of the answer.
 * @param created The date and time that the answer was created.
 * @param lastUpdated The date and time that the answer was last updated.
 * @param id The id of the answer.
 * @param author The author of the answer.
 * @param upVote The number of up votes that the answer has.
 * @param downVote The number of down votes that the answer has.
 * @param matter Unknown
 * @param hidden Whether the answer is hidden.
 * @param active Whether the answer is active.
 * @param slug The slug of the matter.
 * @param avatarUrl The url of the author's avatar.
 * */
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
