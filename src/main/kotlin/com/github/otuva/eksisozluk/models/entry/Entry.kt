package com.github.otuva.eksisozluk.models.entry

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single entry
 *
 * @param id Entry id
 * @param content Entry body
 * @param author Entry author
 * @param created Entry creation date time
 * @param isFavorite Is entry favorited by current user
 * @param favoriteCount Entry favorite count
 * @param hidden Is entry hidden to other users
 * @param active Is entry visible to other users
 * @param commentCount Entry comment count
 * @param commentSummary Entry comment summary
 * @param lastUpdated Entry last updated date time
 * @param avatarUrl Entry author avatar url
 * @param media Images included in entry. List of urls to images
 * @param isSponsored Is entry sponsored
 * @param isPinned Is entry pinned
 * @param isPinnedOnProfile Is entry pinned on profile
 * @param isVerifiedAccount Is entry author verified
 *
 * @see UserIdentifier
 * @see LocalDateTime
 * */
@Serializable
public data class Entry(
    @SerialName("Id") val id: Int,
    @SerialName("Content") val content: String,
    @SerialName("Author") val author: UserIdentifier,
    @SerialName("Created") val created: LocalDateTime,
    @SerialName("IsFavorite") val isFavorite: Boolean,
    @SerialName("FavoriteCount") val favoriteCount: Int,
    @SerialName("Hidden") val hidden: Boolean,
    @SerialName("Active") val active: Boolean,
    @SerialName("CommentCount") val commentCount: Int,
    @SerialName("CommentSummary") val commentSummary: String?,
    @SerialName("LastUpdated") val lastUpdated: LocalDateTime?,
    @SerialName("AvatarUrl") val avatarUrl: String?,
    @SerialName("Media") val media: List<String>?,
    @SerialName("IsSponsored") val isSponsored: Boolean,
    @SerialName("IsPinned") val isPinned: Boolean,
    @SerialName("IsPinnedOnProfile") val isPinnedOnProfile: Boolean,
    @SerialName("IsVerifiedAccount") val isVerifiedAccount: Boolean,
)