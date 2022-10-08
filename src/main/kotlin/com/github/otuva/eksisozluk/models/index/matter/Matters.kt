package com.github.otuva.eksisozluk.models.index.matter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Matters(
    @SerialName("Matters") val matters: List<IndexedMatter>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
)
