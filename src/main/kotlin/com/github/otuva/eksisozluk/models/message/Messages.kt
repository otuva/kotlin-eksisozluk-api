package com.github.otuva.eksisozluk.models.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the inbox.
 *
 * @param messages List of conversations.
 * @param pageCount Number of pages in the inbox.
 * @param pageSize Number of conversations per page.
 * @param pageIndex Current page index.
 * */
@Serializable
public data class Messages(
    @SerialName("Messages") val messages: List<Message>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
)
