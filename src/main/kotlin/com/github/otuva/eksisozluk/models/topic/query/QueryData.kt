package com.github.otuva.eksisozluk.models.topic.query

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class QueryData(
    @SerialName("DraftEntry") val draftEntry: String? = null,
    @SerialName("TopicId") val topicId: Int,
    @SerialName("SuggestedTitle") val suggestedTitle: String? = null,
)
