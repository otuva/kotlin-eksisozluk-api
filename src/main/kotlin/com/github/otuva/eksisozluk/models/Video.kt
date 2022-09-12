package com.github.otuva.eksisozluk.models

import kotlinx.serialization.json.*

/**
 * Represents the video in topic.
 *
 * @param displayInfo Information about the video.
 * @param inTopicVideo Will be true if there's a video in the topic.
 *
 * @see [DisplayInfo]
 * */
data class Video(
    val displayInfo: DisplayInfo?,
    val inTopicVideo: Boolean?,
)

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

/**
 * Parses a JSON string to [Video] object.
 *
 * @param json JSON string.
 *
 * @return [Video] object.
 * */
internal fun deserializeVideo(json: String): Video {
    val jsonElement = Json.parseToJsonElement(json)

    val displayInfo = if (jsonElement.jsonObject["DisplayInfo"] != JsonNull) deserializeDisplayInfo(jsonElement.jsonObject["DisplayInfo"].toString()) else null
    val inTopicVideo = jsonElement.jsonObject["InTopicVideo"]?.jsonPrimitive?.boolean

    return Video(
        displayInfo=displayInfo,
        inTopicVideo=inTopicVideo
    )
}

/**
 * Parses a JSON string to [DisplayInfo] object.
 * Note that this function will not be executed if there's no video in the topic.
 * So it's guaranteed that these keys will exist in the JSON string.
 *
 * @param json JSON string.
 *
 * @return [DisplayInfo] object.
 * */
private fun deserializeDisplayInfo(json: String): DisplayInfo {
    val jsonElement = Json.parseToJsonElement(json)

    val id = jsonElement.jsonObject["Id"]!!.jsonPrimitive.int
    val externalId = jsonElement.jsonObject["ExternalId"]!!.jsonPrimitive.int
    val title = jsonElement.jsonObject["Title"]!!.jsonPrimitive.contentOrNull
    val description = jsonElement.jsonObject["Description"]!!.jsonPrimitive.contentOrNull
    val thumbUri = jsonElement.jsonObject["ThumbUri"]!!.jsonPrimitive.contentOrNull
    val bigThumbUri = jsonElement.jsonObject["BigThumbUri"]!!.jsonPrimitive.contentOrNull
    val fileUri = jsonElement.jsonObject["FileUri"]!!.jsonPrimitive.contentOrNull
    val embeddedVideoUri = jsonElement.jsonObject["EmbeddedVideoUri"]!!.jsonPrimitive.content
    val options = jsonElement.jsonObject["Options"]!!.jsonPrimitive.int
    val isAdvertorial = jsonElement.jsonObject["IsAdvertorial"]!!.jsonPrimitive.boolean
    val pollsEnabled = jsonElement.jsonObject["PollsEnabled"]!!.jsonPrimitive.boolean
    val playVideoInTopic = jsonElement.jsonObject["PlayVideoInTopic"]!!.jsonPrimitive.boolean
    val clickToPlay = jsonElement.jsonObject["ClickToPlay"]!!.jsonPrimitive.boolean
    val hasEmbeddedVideoLink = jsonElement.jsonObject["HasEmbeddedVideoLink"]!!.jsonPrimitive.boolean

    return DisplayInfo(
        id=id,
        externalId=externalId,
        title=title,
        description=description,
        thumbUri=thumbUri,
        bigThumbUri=bigThumbUri,
        fileUri=fileUri,
        embeddedVideoUri=embeddedVideoUri,
        options=options,
        isAdvertorial=isAdvertorial,
        pollsEnabled=pollsEnabled,
        playVideoInTopic=playVideoInTopic,
        clickToPlay=clickToPlay,
        hasEmbeddedVideoLink=hasEmbeddedVideoLink
    )
}