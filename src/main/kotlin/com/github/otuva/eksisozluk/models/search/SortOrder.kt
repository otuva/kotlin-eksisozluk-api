package com.github.otuva.eksisozluk.models.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the order of the search results.
 *
 * @param value The value of the sort order
 * */
@Serializable
public enum class SortOrder(public val value: Int) {
    @SerialName("0")
    Alphabetical(0),

    @SerialName("1")
    ReverseChronological(1),

    @SerialName("2")
    Popularity(2),
}