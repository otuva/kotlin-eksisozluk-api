package com.github.otuva.eksisozluk.models

// import kotlinx.serialization.json.*
import kotlinx.serialization.json.*

// @Serializable
data class Video(
    val displayInfo: DisplayInfo?,
    val inTopicVideo: Boolean?,
)

// @Serializable
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

internal fun deserializeVideo(json: String): Video {
    val jsonElement = Json.parseToJsonElement(json)

    val displayInfo = if (jsonElement.jsonObject["DisplayInfo"] != JsonNull) deserializeDisplayInfo(jsonElement.jsonObject["DisplayInfo"].toString()) else null
    val inTopicVideo = jsonElement.jsonObject["InTopicVideo"]?.jsonPrimitive?.boolean

    return Video(
        displayInfo=displayInfo,
        inTopicVideo=inTopicVideo
    )
}

private fun deserializeDisplayInfo(json: String): DisplayInfo {
    /*
     * this function will not be executed if  displayinfo is null.
     * So it's guaranteed that these keys will exist
     * */
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