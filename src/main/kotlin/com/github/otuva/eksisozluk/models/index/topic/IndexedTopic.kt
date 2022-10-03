package com.github.otuva.eksisozluk.models.index.topic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class IndexedTopic(
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("TopicId") val topicId: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Title") val title: String,
)