package com.github.otuva.eksisozluk.models.index

import com.github.otuva.eksisozluk.models.index.topic.PinnedIndexItem
import com.github.otuva.eksisozluk.models.index.topic.TodaysTopic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the today's index page.
 *
 * @param topics List of topics in the index.
 * @param pageCount Total number of pages in the index.
 * @param pageSize Number of topics in a page.
 * @param pageIndex Current page index.
 * @param hasPinnedIndexItem Whether the index has a pinned topic.
 * @param pinnedIndexItem Pinned topic.
 * */
@Serializable
public data class IndexToday(
    @SerialName("Topics") val topics: List<TodaysTopic>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
    @SerialName("HasPinnedIndexItem") val hasPinnedIndexItem: Boolean,
    @SerialName("PinnedIndexItem") val pinnedIndexItem: PinnedIndexItem?,
)
