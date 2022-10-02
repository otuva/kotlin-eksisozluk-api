package com.github.otuva.eksisozluk.models.index.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Filters(
    @SerialName("Filters") val filters: List<Filter>
)
