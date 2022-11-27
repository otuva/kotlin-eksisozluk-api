package com.github.otuva.eksisozluk.models.index.olay

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents followed topics and matters (olay) itself.
 *
 * @param topics The list of topics that are followed by the user.
 * @param pageCount The total number of pages.
 * @param pageSize The number of items in a page.
 * @param pageIndex The current page.
 * */
@Serializable
public data class Olay(
    @SerialName("Topics") val topics: List<OlayResult>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
)
