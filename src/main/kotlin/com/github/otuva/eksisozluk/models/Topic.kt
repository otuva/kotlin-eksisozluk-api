// TODO
// examples to test:
// 7154265 -> pinned entry topic
// 6362411 -> video topic
// 31872 -> disambiguation topic

package com.github.otuva.eksisozluk.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

/**
 * Represents a topic.
 * Whether it is a whole topic or just a part (page) of a topic is matter of the number of entries.
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
    val id: Int,
    val title: String,
    val entries: List<Entry>,
    val pageCount: Int,
    val pageSize: Int,
    val pageIndex: Int,
    val pinnedEntry: Entry?,
    val entryCounts: TopicEntryCounts,
    val draftEntry: DraftEntry?,
    val isTracked: Boolean,
    val isTrackable: Boolean,
    val slug: String,
    val video: Video?,
    val disambiguations: List<Disambiguation>,
    val isAmaTopic: Boolean,
    val matterCount: Int,
) {
    fun getFirstEntry(): Entry {
        return entries[0]
    }
}

/**
 * Represents the state of entries in a topic.
 * @param beforeFirstEntry The number of entries before the first focused entry in the topic.
 * @param afterLastEntry The number of entries after the last focused entry in the topic.
 * @param buddy The number of entries that are written by followed authors.
 * @param total The total number of entries in the topic.
 * */
@Serializable
data class TopicEntryCounts(
    val beforeFirstEntry: Int,
    val afterLastEntry: Int,
    val buddy: Int,
    val total: Int
)

/**
 * Represents a draft entry in a topic. Note that this is for registered users only.
 * @param content The content of the drafted entry.
 * @param created The creation date of the draft.
 * */
@Serializable
data class DraftEntry(
    val content: String,
    val created: LocalDateTime
)

/**
 * TODO: add disambiguation handler function then add see annotation
 * Represents disambiguation in a topic.
 * In order to handle redirections title and kind of the disambiguation is used instead of id.
 * Because api doesn't return id of the disambiguation.
 * @param title The title of the disambiguation.
 * @param kind The kind of the disambiguation. Could be 'sozluk yazari', 'dizi', 'oyun' etc.
 * */
@Serializable
data class Disambiguation(
    val title: String,
    val kind: String
)

fun deserializeTopic(json: String): Topic {
    val jsonElement = Json.parseToJsonElement(json)

    val id = jsonElement.jsonObject["Id"]!!.jsonPrimitive.int
    val title = jsonElement.jsonObject["Title"]!!.jsonPrimitive.content
    val entries = jsonElement.jsonObject["Entries"]!!.jsonArray.map { deserializeEntry(it.toString()) }
    val pageCount = jsonElement.jsonObject["PageCount"]!!.jsonPrimitive.int
    val pageSize = jsonElement.jsonObject["PageSize"]!!.jsonPrimitive.int
    val pageIndex = jsonElement.jsonObject["PageIndex"]!!.jsonPrimitive.int
    val pinnedEntry = if (jsonElement.jsonObject["PinnedEntry"] != JsonNull) deserializeEntry(jsonElement.jsonObject["PinnedEntry"].toString()) else null
    val entryCounts = deserializeEntryCounts(jsonElement.jsonObject["EntryCounts"]!!.toString())
    val draftEntry = if (jsonElement.jsonObject["DraftEntry"] != JsonNull) deserializeDraftEntry(jsonElement.jsonObject["DraftEntry"].toString()) else null
    val isTracked = jsonElement.jsonObject["IsTracked"]!!.jsonPrimitive.boolean
    val isTrackable = jsonElement.jsonObject["IsTrackable"]!!.jsonPrimitive.boolean
    val slug = jsonElement.jsonObject["Slug"]!!.jsonPrimitive.content
    // eger entry topic responseu ise video olsa bile videoya null diyor
    val video = if ( jsonElement.jsonObject["Video"] != JsonNull ) deserializeVideo(jsonElement.jsonObject["Video"]!!.toString()) else null
    val disambiguations = jsonElement.jsonObject["Disambiguations"]!!.jsonArray.map { deserializeDisambiguation(it.toString()) }
    val isAmaTopic = jsonElement.jsonObject["IsAmaTopic"]!!.jsonPrimitive.boolean
    val matterCount = jsonElement.jsonObject["MatterCount"]!!.jsonPrimitive.int



    return Topic(
        id=id,
        title=title,
        entries=entries,
        pageCount=pageCount,
        pageSize=pageSize,
        pageIndex=pageIndex,
        pinnedEntry=pinnedEntry,
        entryCounts=entryCounts,
        draftEntry=draftEntry,
        isTracked=isTracked,
        isTrackable=isTrackable,
        slug=slug,
        video=video,
        disambiguations=disambiguations,
        isAmaTopic=isAmaTopic,
        matterCount=matterCount
    )
}

private fun deserializeEntryCounts(json: String): TopicEntryCounts {
    val jsonElement = Json.parseToJsonElement(json)

    val beforeFirstEntry = jsonElement.jsonObject["BeforeFirstEntry"]!!.jsonPrimitive.int
    val afterLastEntry = jsonElement.jsonObject["AfterLastEntry"]!!.jsonPrimitive.int
    val buddy = jsonElement.jsonObject["Buddy"]!!.jsonPrimitive.int
    val total = jsonElement.jsonObject["Total"]!!.jsonPrimitive.int

    return TopicEntryCounts(
        beforeFirstEntry = beforeFirstEntry,
        afterLastEntry = afterLastEntry,
        buddy = buddy,
        total = total
    )
}

private fun deserializeDraftEntry(json: String): DraftEntry {
    val jsonElement = Json.parseToJsonElement(json)

    val content = jsonElement.jsonObject["Content"]!!.jsonPrimitive.content
    val created = LocalDateTime.parse(jsonElement.jsonObject["Created"]!!.jsonPrimitive.content)

    return DraftEntry(
        content = content,
        created = created
    )
}

private fun deserializeDisambiguation(json: String): Disambiguation {
    val jsonElement = Json.parseToJsonElement(json)

    val title = jsonElement.jsonObject["Title"]!!.jsonPrimitive.content
    val kind = jsonElement.jsonObject["Kind"]!!.jsonPrimitive.content

    return Disambiguation(
        title = title,
        kind = kind
    )
}
