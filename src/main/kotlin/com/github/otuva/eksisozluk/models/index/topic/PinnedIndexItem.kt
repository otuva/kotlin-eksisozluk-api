package com.github.otuva.eksisozluk.models.index.topic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a pinned topic in the index page. Such as AMA
 *
 * @param title The title of the topic.
 * @param topicId The id of the topic.
 * */
@Serializable
public data class PinnedIndexItem(
    @SerialName("Title") val title: String,
    @SerialName("TopicId") val topicId: Int,
)