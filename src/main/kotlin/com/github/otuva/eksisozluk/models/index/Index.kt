package com.github.otuva.eksisozluk.models.index

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a page of the index.
 * */
@Serializable
public data class Index(
    @SerialName("Topics") val topics: List<IndexedTopic>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
    @SerialName("HasPinnedIndexItem") val hasPinnedIndexItem: Boolean,
    @SerialName("PinnedIndexItem") val pinnedIndexItem: PinnedIndexItem?,
)
