package com.github.otuva.eksisozluk.responses.topic

import com.github.otuva.eksisozluk.responses.GenericResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FollowResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: GenericResponse
)
