package com.github.otuva.eksisozluk.models.index.matter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the called matter endpoint itself.
 *
 * @param matters List of matters in the index page.
 * @param pageCount Total number of pages in the index page.
 * @param pageSize Number of matters in a single page.
 * @param pageIndex Current page index.
 * */
@Serializable
public data class Matters(
    @SerialName("Matters") val matters: List<IndexedMatter>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
)
