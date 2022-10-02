package com.github.otuva.eksisozluk.responses.index

import com.github.otuva.eksisozluk.models.index.Index
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class IndexResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: Index
)