package com.github.otuva.eksisozluk.models.user

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user's information
 * @param userIdentifier User's nick-id pair
 * @param remainingInvitation undocumented/unknown. could be a moderator's invitation count
 * @param twitterScreenName User's twitter handle
 * @param facebookProfileUrl User's facebook profile url
 * @param facebookScreenName User's facebook screen name
 * @param instagramScreenName User's instagram screen name
 * @param instagramProfileUrl User's instagram profile url
 * @param karma User's karma shown on the profile page
 * @param entryCounts User's entry counts by time intervals
 * @param lastEntryDate User's last written entry date
 * @param standingQueueNumber User's standing queue number (caylak sirasi) note that this will be 0 for authors
 *
 * */
@Serializable
public data class UserInfo(
    @SerialName("UserIdentifier") val userIdentifier: UserIdentifier,
    @SerialName("RemainingInvitation") val remainingInvitation: Int,
    @SerialName("TwitterScreenName") val twitterScreenName: String?,
    @SerialName("FacebookProfileUrl") val facebookProfileUrl: String?,
    @SerialName("FacebookScreenName") val facebookScreenName: String?,
    @SerialName("InstagramScreenName") val instagramScreenName: String?,
    @SerialName("InstagramProfileUrl") val instagramProfileUrl: String?,
    @SerialName("Karma") val karma: Karma?,
    @SerialName("EntryCounts") val entryCounts: EntryCounts,
    @SerialName("LastEntryDate") val lastEntryDate: LocalDateTime?,
    @SerialName("StandingQueueNumber") val standingQueueNumber: Int,
    @SerialName("HasAnyPendingInvitation") val hasAnyPendingInvitation: Boolean,
    @SerialName("IsBuddy") val isBuddy: Boolean,
    @SerialName("IsBlocked") val isBlocked: Boolean,
    @SerialName("IsFollowed") val isFollowed: Boolean,
    @SerialName("IsCorporate") val isCorporate: Boolean,
    @SerialName("IsDeactivated") val isDeactivated: Boolean,
    @SerialName("IsKarmaShown") val isKarmaShown: Boolean,
    @SerialName("IsCaylak") val isCaylak: Boolean,
    @SerialName("IsIndexTitlesBlocked") val isIndexTitlesBlocked: Boolean,
    @SerialName("Note") val note: String?,
    @SerialName("CursePeriod") val cursePeriod: CursePeriod?,
    @SerialName("IsCursed") val isCursed: Boolean,
    @SerialName("IsBanned") val isBanned: Boolean,
    @SerialName("DisplayTwitterProfile") val displayTwitterProfile: Boolean,
    @SerialName("DisplayFacebookProfile") val displayFacebookProfile: Boolean,
    @SerialName("DisplayInstagramProfile") val displayInstagramProfile: Boolean,
)