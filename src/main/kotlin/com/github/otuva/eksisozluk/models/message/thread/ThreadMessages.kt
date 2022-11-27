package com.github.otuva.eksisozluk.models.message.thread

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a page of messages in a thread.
 *
 * @param messages The messages in the page.
 * @param pageCount The number of pages in the thread.
 * @param pageSize The number of messages in a page.
 * @param pageIndex The index of the page.
 * @param rowCount The number of messages in the thread.
 * */
@Serializable
public data class ThreadMessages(
    @SerialName("Messages") val messages: List<ThreadMessage>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
    @SerialName("RowCount") val rowCount: Int
)
