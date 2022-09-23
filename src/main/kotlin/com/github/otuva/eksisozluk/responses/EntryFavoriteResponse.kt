package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.models.entry.favorite.EntryFavoriteData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class EntryFavoriteResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: EntryFavoriteData
)
