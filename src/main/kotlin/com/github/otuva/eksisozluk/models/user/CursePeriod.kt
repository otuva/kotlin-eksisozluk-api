package com.github.otuva.eksisozluk.models.user

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a time period of the curse
 *
 * @param from Curse start date
 * @param to Curse end date
 * */
@Serializable
data class CursePeriod(
    @SerialName("From") val from: LocalDateTime,
    @SerialName("To") val to: LocalDateTime
)