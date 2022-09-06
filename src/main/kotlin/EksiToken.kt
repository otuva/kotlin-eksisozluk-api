package com.exanple

//import kotlinx.serialization.Serializable
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlin.time.Duration.Companion.seconds

fun deserializeAnon(response: String): EksiToken {
    val jsonElement = Json.parseToJsonElement(response)

    val success = jsonElement.jsonObject["Success"]!!.jsonPrimitive.boolean
    val message = jsonElement.jsonObject["Message"]!!.jsonPrimitive.content
    val actualData = jsonElement.jsonObject["Data"].toString()

    return deserialize(actualData)
}

fun deserialize(response: String): EksiToken {
    val jsonElement = Json.parseToJsonElement(response)

    val elementRank = jsonElement.jsonObject["rank"]!!.jsonPrimitive.int
    val elementAccessToken = jsonElement.jsonObject["access_token"]!!.jsonPrimitive.content
    val elementTokenType = jsonElement.jsonObject["token_type"]!!.jsonPrimitive.content
    val elementExpiresIn = jsonElement.jsonObject["expires_in"]!!.jsonPrimitive.int
    val elementRefreshToken = jsonElement.jsonObject["refresh_token"]?.jsonPrimitive?.content
    val elementNick = jsonElement.jsonObject["nick"]?.jsonPrimitive?.content
    val elementUserId = jsonElement.jsonObject["user_id"]?.jsonPrimitive?.int

    return EksiToken(
        rank=elementRank,
        accessToken=elementAccessToken,
        tokenType=elementTokenType,
        expiresIn=elementExpiresIn,
        refreshToken=elementRefreshToken,
        nick=elementNick,
        userId=elementUserId
    )
}

fun serialize(token: EksiToken): String {
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