package com.github.otuva.eksisozluk.models.entry.favorite

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents list of users who favorited an entry.
 *
 * @param entryId The id of the entry.
 * @param buddies The list of followed users who favorited the entry.
 * @param authors The list of users who favorited the entry.
 * @param caylakCount Number of caylak users who favorited the entry. This will be generally shown after the list of users.
 * */
@Serializable
public data class Favorites(
    @SerialName("EntryId") val entryId: Int,
    @SerialName("Buddies") val buddies: List<UserIdentifier>,
    @SerialName("Authors") val authors: List<UserIdentifier>,
    @SerialName("CaylakCount") val caylakCount: Int,
)
