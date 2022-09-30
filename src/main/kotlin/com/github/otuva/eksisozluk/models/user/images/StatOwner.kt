package com.github.otuva.eksisozluk.models.user.images

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class StatOwner(
    @SerialName("Nick") val nick: String,
    @SerialName("Id") val id: Int,
)
