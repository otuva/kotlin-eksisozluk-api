package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("Success") val success: Boolean?,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: User
)