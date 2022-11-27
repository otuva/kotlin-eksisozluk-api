package com.github.otuva.eksisozluk.models.index.debe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single topic in debe.
 *
 * @param entryId ID of the entry.
 * @param title Title of the topic.
 * */
@Serializable
public data class DebeItem(
    @SerialName("EntryId") val entryId: Int,
    @SerialName("Title") val title: String,
)
