package com.github.otuva.eksisozluk.models.matter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A single page of a matter.
 *
 * @param rowCount The total number of answers that the matter has.
 * @param index The index of the page.
 * @param size The number of answers that the page has.
 * @param isFirst Whether the page is the first page.
 * @param isLast Whether the page is the last page.
 * @param rowCountBefore The number of answers that the page has before the current page.
 * @param rowCountAfter The number of answers that the page has after the current page.
 * @param items The answers of the page.
 * */
@Serializable
public data class Page(
    @SerialName("RowCount") val rowCount: Int,
    @SerialName("Index") val index: Int,
    @SerialName("Size") val size: Int,
    @SerialName("Count") val count: Int,
    @SerialName("IsFirst") val isFirst: Boolean,
    @SerialName("IsLast") val isLast: Boolean,
    @SerialName("RowCountBefore") val rowCountBefore: Int,
    @SerialName("RowCountAfter") val rowCountAfter: Int,
    @SerialName("Items") val items: List<MatterItem>,
)