package com.github.otuva.eksisozluk.models.entry.favorite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents entry favorite data.
 *
 * Used after favoriting an entry.
 *
 * @param success Whether the request was successful.
 * @param message Response message.
 * @param count Number of updated favorites.
 * */
@Serializable
public data class FavoriteData(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Count") val count: Int
)
