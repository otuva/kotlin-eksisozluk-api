package com.github.otuva.eksisozluk.models.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Messages(
    @SerialName("Messages") val messages: List<Message>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
)
