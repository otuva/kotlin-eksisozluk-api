package com.github.otuva.eksisozluk.responses.index

import com.github.otuva.eksisozluk.models.index.IndexToday
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class IndexTodayResponse(
    @SerialName("Success") val success: Boolean,
    @SerialName("Message") val message: String?,
    @SerialName("Data") val data: IndexToday
)