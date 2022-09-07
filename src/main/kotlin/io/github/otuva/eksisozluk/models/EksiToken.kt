package io.github.otuva.eksisozluk.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlin.time.Duration.Companion.seconds

/**
 * @param rank User rank. 0 is for anonymous users.
 * @param accessToken Access token for API requests.
 * @param tokenType Token type. Always "Bearer".
 * @param expiresIn Token expiration time in seconds.
 * @param refreshToken Refresh token for API requests. Only available for registered users.
 * @param nick User nick. Only available for registered users.
 * @param userId User ID. Only available for registered users.
 * @param issuedAt Token issue time to handle expiration and refresh.
 * @param expiresAt Time instant of when the token expires. Calculated by adding [expiresIn] to [issuedAt].
 * */
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

fun deserializeAuth(json: String): EksiToken {
    val jsonElement = Json.parseToJsonElement(json)

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
