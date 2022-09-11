package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.models.EksiToken
import com.github.otuva.eksisozluk.models.deserializeAuth
// import kotlinx.serialization.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Represents the response of the anonymous login request. Success and message fields are useless.
 *
 * @param success true if login is successful
 * @param message the message of the response always null
 * @param data actual token returned.
 *
 * @see EksiToken
 */
// @Serializable
data class AnonLoginResponse(
    val success: Boolean,
    val message: String?,
    val data: EksiToken
)

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
