package com.github.otuva.eksisozluk.models.topic.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the information about the video. Will be null if there's no video in the topic.
 *
 * @param id Unknown & unused. Always 0
 * @param externalId Unknown & unused. Always 0
 * @param title Unknown & unused.
 * @param description Unknown & unused.
 * @param thumbUri Unknown & unused.
 * @param bigThumbUri Unknown & unused.
 * @param fileUri Unknown & unused.
 * @param embeddedVideoUri Youtube embedded video uri.
 * @param options Unknown & unused. Always 16.
 * @param isAdvertorial Unknown & unused.
 * @param pollsEnabled Unknown & unused.
 * @param playVideoInTopic Will be true if there's a video in the topic.
 * @param clickToPlay Unknown & unused.
 * @param hasEmbeddedVideoLink Whether the video is embedded or not.
 * */
@Serializable
public data class DisplayInfo(
    @SerialName("Id") val id: Int,
    @SerialName("ExternalId") val externalId: Int,
    @SerialName("Title") val title: String?,
    @SerialName("Description") val description: String?,
    @SerialName("ThumbUri") val thumbUri: String?,
    @SerialName("BigThumbUri") val bigThumbUri: String?,
    @SerialName("FileUri") val fileUri: String?,
    @SerialName("EmbeddedVideoUri") val embeddedVideoUri: String,
    @SerialName("Options") val options: Int,
    @SerialName("IsAdvertorial") val isAdvertorial: Boolean,
    @SerialName("PollsEnabled") val pollsEnabled: Boolean,
    @SerialName("PlayVideoInTopic") val playVideoInTopic: Boolean,
    @SerialName("ClickToPlay") val clickToPlay: Boolean,
    @SerialName("HasEmbeddedVideoLink") val hasEmbeddedVideoLink: Boolean
)