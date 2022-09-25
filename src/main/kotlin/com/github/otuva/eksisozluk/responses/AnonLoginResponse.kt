package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.models.authentication.EksiToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the response of the anonymous login request. Success and message fields are useless.
 *
 * @param success true if login is successful
 * @param message the message of the response always null
 * @param data actual token returned.
 *
 * @see EksiToken
 */
@Serializable
public data class AnonLoginResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: EksiToken
)