package com.github.otuva.eksisozluk.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a general response from Eksi Sozluk API.
 *
 * @param success Whether the request was successful or not.
 * @param message The message returned by the API.
 * @param data The data returned by the API.
 * */
@Serializable
public data class GenericResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String,
    @SerialName("Data") val data: Unit?
)