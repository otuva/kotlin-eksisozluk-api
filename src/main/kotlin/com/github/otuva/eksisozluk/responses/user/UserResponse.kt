package com.github.otuva.eksisozluk.responses.user

import com.github.otuva.eksisozluk.models.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserResponse(
    @SerialName("Success") val success: Boolean?,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: User
)