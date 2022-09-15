package com.github.otuva.eksisozluk.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a topic.
 * Whether it is a whole topic or just a part (page) of a topic is matter of the number of entries.
 *
 * @param id topic id
 * @param title topic title
 * @param entries list of entries in the topic
 * @param pageCount number of pages in the topic
 * @param pageSize number of entries in a page
 * @param pageIndex index of the page
 * @param pinnedEntry entry pinned by eksisozluk
 * @param entryCounts state of the entries in the topic things like entries written by buddies etc.
 * @param draftEntry draft entry of the user in the topic
 * @param isTracked whether the topic is followed by the user
 * @param isTrackable can the topic be followed by the user
 * @param slug topic slug that's used before topic id
 * @param video video in the topic
 * @param disambiguations list of disambiguations in the topic
 * @param isAmaTopic whether the topic is 'buyrun benim' topic
 * @param matterCount number of 'sorunsallar' in the topic
 *
 * @see Entry
 * @see TopicEntryCounts
 * @see DraftEntry
 * @see Video
 * @see Disambiguation
 * */
@Serializable
data class Topic(
    @SerialName("Id") val id: Int,
    @SerialName("Title") val title: String,
    @SerialName("Entries") val entries: List<Entry>,
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
    @SerialName("PinnedEntry") val pinnedEntry: Entry?,
    @SerialName("EntryCounts") val entryCounts: TopicEntryCounts,
    @SerialName("DraftEntry") val draftEntry: DraftEntry?,
    @SerialName("IsTracked") val isTracked: Boolean,
    @SerialName("IsTrackable") val isTrackable: Boolean,
    @SerialName("Slug") val slug: String?,
    @SerialName("Video") val video: Video?,
    @SerialName("Disambiguations") val disambiguations: List<Disambiguation>,
    @SerialName("IsAmaTopic") val isAmaTopic: Boolean,
    @SerialName("MatterCount") val matterCount: Int,
) {
    /**
     * Returns the first entry in the topic.
     *
     * @return first [Entry] in the topic
     * */
    fun getFirstEntry(): Entry {
        return entries[0]
    }
}

/**
 * Represents the state of entries in a topic.
 *
 * @param beforeFirstEntry The number of entries before the first focused entry in the topic.
 * @param afterLastEntry The number of entries after the last focused entry in the topic.
 * @param buddy The number of entries that are written by followed authors.
 * @param total The total number of entries in the topic.
 * */
@Serializable
data class TopicEntryCounts(
    @SerialName("BeforeFirstEntry") val beforeFirstEntry: Int,
    @SerialName("AfterLastEntry") val afterLastEntry: Int,
    @SerialName("Buddy") val buddy: Int,
    @SerialName("Total") val total: Int
)

/**
 * Represents a draft entry in a topic. Note that this is for registered users only.
 *
 * @param content The content of the drafted entry.
 * @param created The creation date of the draft.
 * */
@Serializable
data class DraftEntry(
    @SerialName("Content") val content: String,
    @SerialName("Created") val created: LocalDateTime
)

/**
 * Represents disambiguation in a topic.
 * In order to handle redirections title and kind of the disambiguation is used respectively instead of topic id.
 * Because api doesn't return id of the disambiguation.
 *
 * @param title The title of the disambiguation.
 * @param kind The kind of the disambiguation. Could be 'sozluk yazari', 'dizi', 'oyun' etc.
 *
 *  TODO: add disambiguation handler function then add see annotation
 * */
@Serializable
data class Disambiguation(
    @SerialName("Title") val title: String,
    @SerialName("Kind") val kind: String
)