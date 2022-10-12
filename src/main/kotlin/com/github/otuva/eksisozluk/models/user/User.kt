package com.github.otuva.eksisozluk.models.user

import com.github.otuva.eksisozluk.models.topic.Topic
import com.github.otuva.eksisozluk.models.user.badge.Badge
import com.github.otuva.eksisozluk.models.user.badge.ImageBadge
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user
 *
 * @param userInfo User information represented by [UserInfo]
 * @param badges User badges. Note that api returns an empty list unless user is 'caylak' or 'leyla' etc.
 * @param hasEntryUsedOnSeyler Whether the user has an entry used on 'EksiSeyler'
 * @param followerCount Number of followers
 * @param followingsCount Number of users the user is following
 * @param picture Link to the user's profile picture
 * @param pinnedEntry This parameter is useless. It is always null. To see pinned entry use user's last entries
 *
 * @see UserInfo
 * */
@Serializable
public data class User(
    @SerialName("UserInfo") val userInfo: UserInfo,
    @SerialName("Badges") val badges: List<Badge>,
    @SerialName("ImageBadges") val imageBadges: List<ImageBadge>,
    @SerialName("HasEntryUsedOnSeyler") val hasEntryUsedOnSeyler: Boolean,
    @SerialName("FollowerCount") val followerCount: Int,
    @SerialName("FollowingsCount") val followingsCount: Int,
    @SerialName("Picture") val picture: String?,
    @SerialName("PinnedEntry") val pinnedEntry: Topic?,
    @SerialName("Biograpyh") val biography: String?
)