package com.github.otuva.eksisozluk.models.search

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Used for advanced search.
 *
 * @param keywords Keywords to search for.
 * @param from The starting date of the search. Can be null
 * @param to The ending date of the search. Can be null
 * @param author Entries written by this author. Can be null
 * @param sortOrder The order of the results
 * @param niceOnly Only 'nice' entries. Default is false
 * @param favoritedOnly Only search in user's favorites. This requires login
 * */
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