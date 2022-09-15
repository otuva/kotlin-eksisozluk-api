package com.github.otuva.eksisozluk.models.topic.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the video in topic.
 *
 * @param displayInfo Information about the video.
 * @param inTopicVideo Will be true if there's a video in the topic.
 *
 * @see [DisplayInfo]
 * */
@Serializable
data class Video(
    @SerialName("DisplayInfo") val displayInfo: DisplayInfo?,
    @SerialName("InTopicVideo") val inTopicVideo: Boolean?,
)

