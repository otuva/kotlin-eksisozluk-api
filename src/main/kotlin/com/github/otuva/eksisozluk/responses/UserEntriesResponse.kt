package com.github.otuva.eksisozluk.responses

import com.github.otuva.eksisozluk.models.user.entries.UserEntries
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserEntriesResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: UserEntries?
)