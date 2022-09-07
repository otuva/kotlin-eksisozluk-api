package io.github.otuva.eksisozluk.models

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val displayInfo: DisplayInfo?,
    val inTopicVideo: Boolean?,
)

@Serializable
data class DisplayInfo(
    val id: Int,
    val externalId: Int,
    val title: String?,
    val description: String?,
    val thumbUri: String?,
    val bigThumbUri: String?,
    val fileUri: String?,
    val embeddedVideoUri: String,
    val options: Int,
    val isAdvertorial: Boolean,
    val pollsEnabled: Boolean,
    val playVideoInTopic: Boolean,
    val clickToPlay: Boolean,
    val hasEmbeddedVideoLink: Boolean
)