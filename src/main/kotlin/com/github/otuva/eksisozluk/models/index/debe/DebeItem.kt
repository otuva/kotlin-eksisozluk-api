package com.github.otuva.eksisozluk.models.index.debe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class DebeItem(
    @SerialName("EntryId") val entryId: Int,
    @SerialName("Title") val title: String,
)
