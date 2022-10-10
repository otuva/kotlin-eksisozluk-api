package com.github.otuva.eksisozluk.models.message.thread

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ThreadMessages(
    @SerialName("Messages") val messages: List<ThreadMessage>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
    @SerialName("RowCount") val rowCount: Int
)
