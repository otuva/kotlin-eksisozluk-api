package com.github.otuva.eksisozluk.responses.matter

import com.github.otuva.eksisozluk.models.matter.Matter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MatterResponse (
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Matter
)