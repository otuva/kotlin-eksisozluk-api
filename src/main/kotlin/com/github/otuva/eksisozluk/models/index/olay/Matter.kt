package com.github.otuva.eksisozluk.models.index.olay

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("m")
public data class Matter(
    @SerialName("Snapshot") val snapshot: Int,
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("Id") val id: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Detail") val detail: String?,
    @SerialName("MatterTitle") val matterTitle: String,
    @SerialName("Title") val title: String,
): OlayResult()
