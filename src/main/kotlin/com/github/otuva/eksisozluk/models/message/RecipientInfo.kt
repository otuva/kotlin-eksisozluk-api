package com.github.otuva.eksisozluk.models.message

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RecipientInfo(
    @SerialName("CanSend") val canSend: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("LatestEntryDate") val latestEntryDate: LocalDateTime,
    @SerialName("LatestEntryTurkishDateDiff") val latestEntryTurkishDateDiff: String,
)
