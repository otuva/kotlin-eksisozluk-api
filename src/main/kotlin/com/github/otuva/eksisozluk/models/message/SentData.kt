package com.github.otuva.eksisozluk.models.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data of a sent message.
 *
 * @param id ?
 * @param sentId Id of the sent message.
 * */
@Serializable
public data class SentData(
    @SerialName("Id") val id: Int,
    @SerialName("SentId") val sentId: Int,
)
