package com.github.otuva.eksisozluk.models.index

import com.github.otuva.eksisozluk.models.index.topic.PinnedIndexItem
import com.github.otuva.eksisozluk.models.index.topic.TodaysTopic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class IndexToday(
    @SerialName("Topics") val topics: List<TodaysTopic>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
    @SerialName("HasPinnedIndexItem") val hasPinnedIndexItem: Boolean,
    @SerialName("PinnedIndexItem") val pinnedIndexItem: PinnedIndexItem?,
)
