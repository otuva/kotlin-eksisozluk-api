package com.github.otuva.eksisozluk.models.index.topic

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single topic in 'today'.
 *
 * @param day The date time of the topic.
 * @param matchedCount The number of entries in that topic while it's on the today's list.
 * This would be displayed along with the topic name.
 * @param topicId The id of the topic.
 * @param fullCount Total number of entries in the topic.
 * @param title The title of the topic.
 * */
@Serializable
public data class TodaysTopic(
    @SerialName("Day") val day: LocalDateTime,
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("TopicId") val topicId: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Title") val title: String,
)