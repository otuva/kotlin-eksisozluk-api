package io.github.otuva.eksisozluk.responses

import io.github.otuva.eksisozluk.models.EksiToken
import io.github.otuva.eksisozluk.models.deserializeAuth
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

fun deserializeAnonLoginResponse(json: String): AnonLoginResponse {
    val jsonElement = Json.parseToJsonElement(json)

    val success = jsonElement.jsonObject["Success"]!!.jsonPrimitive.boolean
    val message = jsonElement.jsonObject["Message"]!!.jsonPrimitive.content
    val data = deserializeAuth(jsonElement.jsonObject["Data"].toString())

    return AnonLoginResponse(
        success=success,
        message=message,
        data=data
    )
}

@Serializable
data class AnonLoginResponse(
    val success: Boolean,
    val message: String?,
    val data: EksiToken
)