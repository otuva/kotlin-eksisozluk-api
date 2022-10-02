package com.github.otuva.eksisozluk.responses.user

import com.github.otuva.eksisozluk.models.user.images.UserImages
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserImagesResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: UserImages
)
