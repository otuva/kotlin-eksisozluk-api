package com.github.otuva.eksisozluk.models.message

import kotlinx.serialization.Serializable

@Serializable
public data class Send(
    val message: String,
    val threadId: Int,
    val to: String
)
