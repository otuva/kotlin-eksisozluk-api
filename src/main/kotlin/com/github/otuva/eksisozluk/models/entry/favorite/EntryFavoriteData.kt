package com.github.otuva.eksisozluk.models.entry.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class EntryFavoriteData(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Count") val count: Int
)
