package io.github.otuva.eksisozluk.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlin.time.Duration.Companion.seconds

fun deserializeAnonAuth(response: String): EksiToken {
    val jsonElement = Json.parseToJsonElement(response)

    val success = jsonElement.jsonObject["Success"]!!.jsonPrimitive.boolean
    val message = jsonElement.jsonObject["Message"]!!.jsonPrimitive.content
    val actualData = jsonElement.jsonObject["Data"].toString()

    return deserializeAuth(actualData)
}

fun deserializeAuth(response: String): EksiToken {
    val jsonElement = Json.parseToJsonElement(response)

    val rank = jsonElement.jsonObject["rank"]!!.jsonPrimitive.int
    val accessToken = jsonElement.jsonObject["access_token"]!!.jsonPrimitive.content
    val tokenType = jsonElement.jsonObject["token_type"]!!.jsonPrimitive.content
    val expiresIn = jsonElement.jsonObject["expires_in"]!!.jsonPrimitive.int
    val refreshToken = jsonElement.jsonObject["refresh_token"]?.jsonPrimitive?.content
    val nick = jsonElement.jsonObject["nick"]?.jsonPrimitive?.content
    val userId = jsonElement.jsonObject["user_id"]?.jsonPrimitive?.int

    return EksiToken(
        rank=rank,
        accessToken=accessToken,
        tokenType=tokenType,
        expiresIn=expiresIn,
        refreshToken=refreshToken,
        nick=nick,
        userId=userId
    )
}

fun serializeEksiToken(token: EksiToken): String {
    val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    return json.encodeToString(EksiToken.serializer(), token)
}

@Serializable
data class EksiToken(
    val rank: Int,
    val accessToken: String,
    val tokenType:String,
    val expiresIn: Int,
    val userId: Int?,
    val refreshToken: String?,
    val nick: String?,
    val issuedAt: Instant = Clock.System.now(),
    val expiresAt: Instant = issuedAt + expiresIn.seconds
)