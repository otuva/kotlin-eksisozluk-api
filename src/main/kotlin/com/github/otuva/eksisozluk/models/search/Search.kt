package com.github.otuva.eksisozluk.models.search

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Search(
    @SerialName("Keywords") val keywords: String,
    @SerialName("WhenFrom") val from: LocalDateTime?,
    @SerialName("WhenTo") val to: LocalDateTime?,
    @SerialName("Author") val author: String?,
    @SerialName("SortOrder") val sortOrder: SortOrder,
    @SerialName("NiceOnly") val niceOnly: Boolean,
    @SerialName("FavoritedOnly") val favoritedOnly: Boolean,
)