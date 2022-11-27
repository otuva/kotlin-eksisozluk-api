package com.github.otuva.eksisozluk.models.index.olay

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a followed topic in the olay page.
 *
 * @param snapshot The id of the first entry of the topic since the last time it was checked.
 * This is what you call the getSnapshot function with when user clicks on the matter.
 * @param matchedCount The number of entries written since last check.
 * This would be displayed along with the matter name.
 * @param topicId The id of the topic.
 * @param fullCount Total number of entries in the topic.
 * @param title The title of the topic.
 * */
@Serializable
@SerialName("t")
public data class Topic(
    @SerialName("Snapshot") val snapshot: Int,
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("TopicId") val topicId: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Title") val title: String,
): OlayResult()
