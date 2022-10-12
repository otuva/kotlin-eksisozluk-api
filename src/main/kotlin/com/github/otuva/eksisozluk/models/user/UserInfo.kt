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
 * @param instagramScreenName User's instagram username
 * @param instagramProfileUrl User's instagram profile url
 * @param karma User's karma shown on the profile page
 * @param entryCounts User's entry counts by time intervals
 * @param lastEntryDate User's last written entry date
 * @param standingQueueNumber User's standing queue number (caylak sirasi) note that this will be 0 for authors and caylak users can only see their own standing queue number
 * @param hasAnyPendingInvitation Unknown
 * @param isBuddy Whether you are following the user
 * @param isBlocked Whether you have blocked the user
 * @param isFollowed Unknown
 * @param isCorporate Whether the user is a corporate user
 * @param isDeactivated Whether the user has deactivated their account
 * @param isKarmaShown Whether the user's karma is shown on the profile page
 * @param isCaylak Whether the user is a caylak or not
 * @param isIndexTitlesBlocked Whether you have blocked the user's index titles | baslik engelleme
 * @param note Unknown
 * @param cursePeriod Period of the user's curse if the user is cursed else null
 * @param isCursed Whether the user is cursed or not
 * @param isBanned Whether the user is banned or not
 * @param displayTwitterProfile Whether the user's Twitter profile is shown on the profile page
 * @param displayFacebookProfile Whether the user's facebook profile is shown on the profile page
 * @param displayInstagramProfile Whether the user's instagram profile is shown on the profile page
 * @param isBuddyCurrentUser Unknown
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
    @SerialName("IsBuddyCurrentUser") val isBuddyCurrentUser: Boolean,
)