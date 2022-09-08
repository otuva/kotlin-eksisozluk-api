package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.BadUserException
import com.github.otuva.eksisozluk.models.User
import com.github.otuva.eksisozluk.models.deserializeUser
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class UserResponse(
    val success: Boolean?,
    val message: String?,
    val data: User
)

fun deserializeUserResponse(json: String): UserResponse {
    val jsonElement = Json.parseToJsonElement(json)

    val success = jsonElement.jsonObject["Success"]?.jsonPrimitive?.boolean
    val message = jsonElement.jsonObject["Message"]?.jsonPrimitive?.content

    if (success != true && message != null) throw BadUserException(message)

    val data = deserializeUser(jsonElement.jsonObject["Data"].toString())

    return UserResponse(
        success=success,
        message=message,
        data=data
    )
}