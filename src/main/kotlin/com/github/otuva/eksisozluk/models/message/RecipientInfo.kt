package com.github.otuva.eksisozluk.models.message

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information about a recipient.
 *
 * @param canSend Whether the recipient can receive messages.
 * @param message Error message if the recipient can't receive messages. Otherwise, null.
 * @param latestEntryDate Date of the latest entry of the recipient.
 * @param latestEntryTurkishDateDiff Date of the latest entry of the recipient in a human-readable format (e.g. "4 gün önce").
 * */
@Serializable
public data class RecipientInfo(
    @SerialName("CanSend") val canSend: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("LatestEntryDate") val latestEntryDate: LocalDateTime,
    @SerialName("LatestEntryTurkishDateDiff") val latestEntryTurkishDateDiff: String,
)
