package com.github.otuva.eksisozluk.models

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

/**
 * Represent an entry page shown in the users profile page. Note that this is not the same as [Topic]
 *
 * @param topicId the id - title pair of the topic
 * @param entry actual entry in the aforementioned topic
 * */
@Serializable
data class CondensedTopic(
    @SerialName("TopicId") val topicId: TopicId,
    @SerialName("Entry") val entry: Entry
)

/**
 * Represents id - title pair of a topic.
 *
 * @param id the id of the topic
 * @param topicTitle the title - kind of the topic
 * @param title the title of the topic
 * */
@Serializable
data class TopicId(
    @SerialName("Id") val id: Int,
    @SerialName("TopicTitle") val topicTitle: TopicTitle,
    @SerialName("Title") val title: String
)

/**
 * Represents topic - kind pair.
 *
 * @param title the title of the topic
 * @param kind the kind of the topic
 * */
@Serializable
data class TopicTitle(
    @SerialName("Title") val title: String,
    @SerialName("Kind") val kind: String?
)