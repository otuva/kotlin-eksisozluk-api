package com.github.otuva.eksisozluk.models.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SentData(
    @SerialName("Id") val id: Int,
    @SerialName("SentId") val sentId: Int,
)
