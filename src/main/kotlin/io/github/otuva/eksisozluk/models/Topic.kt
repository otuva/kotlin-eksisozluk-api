package io.github.otuva.eksisozluk.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

@Serializable
data class Topic(
    val id: Int,
    val title: String,
    val entries: List<Entry>,
    val pageCount: Int,
    val pageSize: Int,
    val pageIndex: Int,
    val pinnedEntry: Entry?,
    val entryCounts: EntryCounts,
    val draftEntry: DraftEntry?,
    val isTracked: Boolean,
    val isTrackable: Boolean,
    val disambiguations: List<Disambiguation>,
    val isAmaTopic: Boolean,
    val matterCount: Int,
    val slug: String,
    val video: Video,
)

@Serializable
data class EntryCounts(
    val beforeFirstEntry: Int,
    val afterLastEntry: Int,
    val buddy: Int,
    val total: Int
)

@Serializable
data class DraftEntry(
    val content: String,
    val created: LocalDateTime
)

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
    val pageSize = jsonElement.jsonObject["pageSize"]!!.jsonPrimitive.int
    val pageIndex = jsonElement.jsonObject["pageIndex"]!!.jsonPrimitive.int
    val pinnedEntry = jsonElement.jsonObject["PinnedEntry"]?.let { deserializeEntry(it.toString()) }
    val entryCounts = deserializeEntryCounts(jsonElement.jsonObject["EntryCounts"]!!.toString())
    val draftEntry = jsonElement.jsonObject["DraftEntry"]?.let { deserializeDraftEntry(it.toString()) }
    val isTracked = jsonElement.jsonObject["IsTracked"]!!.jsonPrimitive.boolean
    val isTrackable = jsonElement.jsonObject["IsTrackable"]!!.jsonPrimitive.boolean
    val disambiguations = jsonElement.jsonObject["Disambiguations"]!!.jsonArray.map { deserializeDisambiguation(it.toString()) }
    val slug = jsonElement.jsonObject["Slug"]!!.jsonPrimitive.content
    val video = deserializeVideo(jsonElement.jsonObject["Video"]!!.toString())

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
        disambiguations=disambiguations,
        isAmaTopic=false,
        matterCount=0,
        slug=slug,
        video=video
    )
}

private fun deserializeEntryCounts(json: String): EntryCounts {
    val jsonElement = Json.parseToJsonElement(json)

    val beforeFirstEntry = jsonElement.jsonObject["BeforeFirstEntry"]!!.jsonPrimitive.int
    val afterLastEntry = jsonElement.jsonObject["AfterLastEntry"]!!.jsonPrimitive.int
    val buddy = jsonElement.jsonObject["Buddy"]!!.jsonPrimitive.int
    val total = jsonElement.jsonObject["Total"]!!.jsonPrimitive.int

    return EntryCounts(
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
