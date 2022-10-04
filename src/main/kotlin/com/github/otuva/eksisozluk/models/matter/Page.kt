package com.github.otuva.eksisozluk.models.matter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Page(
    @SerialName("RowCount") val rowCount: Int,
    @SerialName("Index") val index: Int,
    @SerialName("Size") val size: Int,
    @SerialName("Count") val count: Int,
    @SerialName("IsFirst") val isFirst: Boolean,
    @SerialName("IsLast") val isLast: Boolean,
    @SerialName("RowCountBefore") val rowCountBefore: Int,
    @SerialName("RowCountAfter") val rowCountAfter: Int,
    @SerialName("Items") val items: List<MatterItem>,
)