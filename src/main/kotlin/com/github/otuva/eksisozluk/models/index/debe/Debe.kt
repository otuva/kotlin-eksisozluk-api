package com.github.otuva.eksisozluk.models.index.debe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Debe(
    @SerialName("Title") val title: String,
    @SerialName("Description") val description: String,
    @SerialName("DebeItems") val debeItems: List<DebeItem>
)
