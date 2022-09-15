package com.github.otuva.eksisozluk.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration.Companion.seconds

/**
 * Represents an authentication token for EksiSozluk API.
 *
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
    val rank: Int? = null,
    @SerialName("access_token") val accessToken: String,
    @SerialName("token_type") val tokenType: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("user_id") val userId: Int? = null,
    @SerialName("refresh_token") val refreshToken: String? = null,
    val nick: String? = null,
    val issuedAt: Instant = Clock.System.now(),
    val expiresAt: Instant = issuedAt + expiresIn.seconds,
)