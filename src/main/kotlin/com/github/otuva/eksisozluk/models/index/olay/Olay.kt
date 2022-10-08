package com.github.otuva.eksisozluk.models.index.olay

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Olay(
    @SerialName("Topics") val topics: List<OlayResult>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
)
