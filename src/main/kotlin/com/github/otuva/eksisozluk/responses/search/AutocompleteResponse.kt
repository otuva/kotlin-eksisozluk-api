package com.github.otuva.eksisozluk.responses.search

import com.github.otuva.eksisozluk.models.search.Autocomplete
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class AutocompleteResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Autocomplete
)
