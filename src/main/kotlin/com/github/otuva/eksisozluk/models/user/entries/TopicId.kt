package com.github.otuva.eksisozluk.models.user.entries

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents id - title pair of a topic.
 *
 * @param id the id of the topic
 * @param topicTitle the title - kind of the topic
 * @param title the title of the topic
 * */
@Serializable
data class TopicId(
    @SerialName("Id") val id: Int,
    @SerialName("TopicTitle") val topicTitle: TopicTitle,
    @SerialName("Title") val title: String
)