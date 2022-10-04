package com.github.otuva.eksisozluk.models.index.matter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class IndexedMatter(
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("Id") val id: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Detail") val detail: String?,
    @SerialName("MatterTitle") val matterTitle: String,
    @SerialName("Title") val title: String,
)
