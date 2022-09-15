package com.github.otuva.eksisozluk.models.user.entries

import com.github.otuva.eksisozluk.models.topic.Topic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single entry page shown in the users profile page.
 *
 * @param pinnedEntry The pinned entry of the user. If the user has no pinned entry, this will be null.
 * This value only used in the main entries written by the user and not used in the stats entries like 'weekly (hafta boyunca oylananlari)' or 'most voted (en cok oylananlari)'.
 * @param entries Entries written by the user. Instead of using standard template api uses different template and is represented by [CondensedTopic]. If the user has no entries, this will be an empty list.
 * @param pageCount Total number of pages.
 * @param pageSize Number of entries per page.
 * @param pageIndex Current page number.
 * */
@Serializable
data class UserEntries(
    @SerialName("PinnedEntry") val pinnedEntry: Topic? = null,
    @SerialName("Entries") val entries: List<CondensedTopic>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int
)