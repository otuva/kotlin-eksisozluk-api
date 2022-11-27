package com.github.otuva.eksisozluk.models.index.topic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent a single topic in the index page.
 *
 * @param matchedCount The number of entries in that topic while it's on the popular list.
 * This would be displayed along with the topic name.
 * @param topicId The id of the topic.
 * @param fullCount Total number of entries in the topic.
 * @param title The title of the topic.
 * */
@Serializable
public data class IndexedTopic(
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("TopicId") val topicId: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Title") val title: String,
)