package com.github.otuva.eksisozluk.responses.matter

import com.github.otuva.eksisozluk.models.index.matter.Matters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MattersResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Matters
)
