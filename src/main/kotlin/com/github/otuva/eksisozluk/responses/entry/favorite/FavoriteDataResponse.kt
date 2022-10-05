package com.github.otuva.eksisozluk.responses.entry.favorite

import com.github.otuva.eksisozluk.models.entry.favorite.FavoriteData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FavoriteDataResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: FavoriteData
)
