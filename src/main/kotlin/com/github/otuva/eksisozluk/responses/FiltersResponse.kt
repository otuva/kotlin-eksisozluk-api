package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.models.index.filter.Filters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FiltersResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Filters
)
