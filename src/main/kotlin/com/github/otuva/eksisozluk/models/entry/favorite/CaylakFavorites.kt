package com.github.otuva.eksisozluk.models.entry.favorite

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents caylak favorites on a entry.
 *
 * @param entryId The id of the entry.
 * @param buddies The list of followed caylak users who favorited the entry.
 * @param authors The list of caylak users who favorited the entry.
 * */
@Serializable
public data class CaylakFavorites(
    @SerialName("EntryId") val entryId: Int,
    @SerialName("Buddies") val buddies: List<UserIdentifier>,
    @SerialName("Caylaks") val authors: List<UserIdentifier>,
)
