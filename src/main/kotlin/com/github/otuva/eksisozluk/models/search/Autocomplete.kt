package com.github.otuva.eksisozluk.models.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the autocomplete results.
 *
 * @param titles List of titles.
 * @param query String that was used for the search.
 * @param nicks List of nicks.
 * */
@Serializable
public data class Autocomplete(
    @SerialName("Titles") val titles: List<String>,
    @SerialName("Query") val query: String,
    @SerialName("Nicks") val nicks: List<String>,
)
