package com.github.otuva.eksisozluk.models.index.topic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PinnedIndexItem(
    @SerialName("Title") val title: String,
    @SerialName("TopicId") val topicId: Int,
)