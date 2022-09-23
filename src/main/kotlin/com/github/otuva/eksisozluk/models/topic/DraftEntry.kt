package com.github.otuva.eksisozluk.models.topic

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a draft entry in a topic. Note that this is for registered users only.
 *
 * @param content The content of the drafted entry.
 * @param created The creation date of the draft.
 * */
@Serializable
public data class DraftEntry(
    @SerialName("Content") val content: String,
    @SerialName("Created") val created: LocalDateTime
)