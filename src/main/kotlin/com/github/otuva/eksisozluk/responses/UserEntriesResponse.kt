package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.models.UserEntries
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntriesResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: UserEntries?
)