package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.BadUserEntriesException
import com.github.otuva.eksisozluk.models.UserEntries
import com.github.otuva.eksisozluk.models.deserializeUserEntries
import kotlinx.serialization.json.*

data class UserEntriesResponse(
    val success: Boolean?,
    val message: String?,
    val data: UserEntries?
)

fun deserializeUserEntriesResponse(json: String): UserEntriesResponse {
    val jsonElement = Json.parseToJsonElement(json)

    val success = jsonElement.jsonObject["Success"]?.jsonPrimitive?.boolean
    val message = jsonElement.jsonObject["Message"]?.jsonPrimitive?.content

    if (success != true && message != null) throw BadUserEntriesException(message)

    val data = if (jsonElement.jsonObject["Data"] != JsonNull) deserializeUserEntries(jsonElement.jsonObject["Data"].toString()) else null

    return UserEntriesResponse(
        success=success,
        message=message,
        data=data
    )
}