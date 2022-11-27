package com.github.otuva.eksisozluk.models.index

import com.github.otuva.eksisozluk.models.index.topic.IndexedTopic
import com.github.otuva.eksisozluk.models.index.topic.PinnedIndexItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an index page. Such as popular, today in the past, etc.
 *
 * @param topics List of topics in the index.
 * @param pageCount Total number of pages in the index.
 * @param pageSize Number of topics in a page.
 * @param pageIndex Current page index.
 * @param hasPinnedIndexItem Whether the index has a pinned topic.
 * @param pinnedIndexItem Pinned topic.
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
