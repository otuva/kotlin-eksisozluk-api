package com.github.otuva.eksisozluk.models.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Autocomplete(
    @SerialName("Titles") val titles: List<String>,
    @SerialName("Query") val query: String,
    @SerialName("Nicks") val nicks: List<String>,
)
