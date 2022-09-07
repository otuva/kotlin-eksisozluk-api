package io.github.otuva.eksisozluk.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

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



fun deserializeEntryCounts(json: String): EntryCounts {
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

fun deserializeDraftEntry(json: String): DraftEntry {
    val jsonElement = Json.parseToJsonElement(json)

    val content = jsonElement.jsonObject["Content"]!!.jsonPrimitive.content
    val created = LocalDateTime.parse(jsonElement.jsonObject["Created"]!!.jsonPrimitive.content)

    return DraftEntry(
        content = content,
        created = created
    )
}
