package com.github.otuva.eksisozluk.models.topic

import com.github.otuva.eksisozluk.models.entry.Entry
import com.github.otuva.eksisozluk.models.topic.video.Video
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