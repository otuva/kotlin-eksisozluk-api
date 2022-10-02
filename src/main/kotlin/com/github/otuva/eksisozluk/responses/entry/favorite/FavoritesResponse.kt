package com.github.otuva.eksisozluk.responses.entry.favorite

import com.github.otuva.eksisozluk.models.entry.favorite.Favorites
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FavoritesResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Favorites
)
