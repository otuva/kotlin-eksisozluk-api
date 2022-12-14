package com.github.otuva.eksisozluk.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user's entry statistics
 *
 * @param total Total number of entries
 * @param lastMonth Number of entries written in the last month
 * @param lastWeek Number of entries written in the last week
 * @param today Number of entries written today
 * */
@Serializable
public data class EntryCounts(
    @SerialName("Total") val total: Int,
    @SerialName("LastMonth") val lastMonth: Int,
    @SerialName("LastWeek") val lastWeek: Int,
    @SerialName("Today") val today: Int
)