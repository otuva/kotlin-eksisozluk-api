package io.github.otuva.eksisozluk.responses

import io.github.otuva.eksisozluk.BadTopicException
import io.github.otuva.eksisozluk.models.Topic
import io.github.otuva.eksisozluk.models.deserializeTopic
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class TopicResponse(
    val success: Boolean?,
    val message: String?,
    val data: Topic
)

fun deserializeTopicResponse(json: String): TopicResponse {
    val jsonElement = Json.parseToJsonElement(json)

    val success = jsonElement.jsonObject["Success"]?.jsonPrimitive?.boolean
    val message = jsonElement.jsonObject["Message"]?.jsonPrimitive?.content

    if (success != true && message != null) throw BadTopicException(message)

    val data = deserializeTopic(jsonElement.jsonObject["Data"].toString())

    return TopicResponse(
        success = success,
        message = message,
        data = data
    )
}