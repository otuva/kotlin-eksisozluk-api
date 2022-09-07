package io.github.otuva.eksisozluk.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class UserIdentifier(
    val nick: String,
    val id: Int
)

//class User {
//}

fun deserializeUserIdentifier(response: String): UserIdentifier {
    val jsonElement = Json.parseToJsonElement(response)

    val nick = jsonElement.jsonObject["Nick"]!!.jsonPrimitive.content
    val id = jsonElement.jsonObject["Id"]!!.jsonPrimitive.int

    return UserIdentifier(
        nick = nick,
        id = id
    )
}