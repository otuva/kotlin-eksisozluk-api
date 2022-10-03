package com.github.otuva.eksisozluk.models.topic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the state of entries in a topic.
 *
 * @param beforeFirstEntry The number of entries before the first focused entry in the topic.
 * @param afterLastEntry The number of entries after the last focused entry in the topic.
 * @param buddy The number of entries that are written by followed authors.
 * @param total The total number of entries in the topic.
 * */
@Serializable
public data class EntryCounts(
    @SerialName("BeforeFirstEntry") val beforeFirstEntry: Int,
    @SerialName("AfterLastEntry") val afterLastEntry: Int,
    @SerialName("Buddy") val buddy: Int,
    @SerialName("Total") val total: Int
)