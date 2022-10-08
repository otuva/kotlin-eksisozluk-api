package com.github.otuva.eksisozluk.models.index.olay

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("t")
public data class Topic(
    @SerialName("Snapshot") val snapshot: Int,
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("TopicId") val topicId: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Title") val title: String,
): OlayResult()
