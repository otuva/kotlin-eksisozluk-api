package com.github.otuva.eksisozluk.models.entry.favorite

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CaylakFavorites(
    @SerialName("EntryId") val entryId: Int,
    @SerialName("Buddies") val buddies: List<UserIdentifier>,
    @SerialName("Caylaks") val authors: List<UserIdentifier>,
)
