package com.github.otuva.eksisozluk.models

import kotlinx.serialization.json.*

data class UserEntries(
    val pinnedEntry: Topic?,
    val entries: List<CondensedTopic>,
    val pageCount: Int,
    val pageSize: Int,
    val pageIndex: Int
)

data class CondensedTopic(
    val topicId: TopicId,
    val entry: Entry
)

data class TopicId(
    val id: Int,
    val topicTitle: TopicTitle,
    val title: String
)

data class TopicTitle(
    val title: String,
    val kind: String?
)

fun deserializeUserEntries(json: String): UserEntries {
    val jsonElement = Json.parseToJsonElement(json)

    val pinnedEntry = if (jsonElement.jsonObject["PinnedEntry"] != JsonNull) deserializeTopic(jsonElement.jsonObject["PinnedEntry"].toString()) else null
    val entries = jsonElement.jsonObject["Entries"]!!.jsonArray.map { deserializeCondensedTopic(it.toString()) }
    val pageCount = jsonElement.jsonObject["PageCount"]!!.jsonPrimitive.int
    val pageSize = jsonElement.jsonObject["PageSize"]!!.jsonPrimitive.int
    val pageIndex = jsonElement.jsonObject["PageIndex"]!!.jsonPrimitive.int

    return UserEntries(
        pinnedEntry=pinnedEntry,
        entries=entries,
        pageCount=pageCount,
        pageSize=pageSize,
        pageIndex=pageIndex
    )
}

fun deserializeCondensedTopic(json: String): CondensedTopic {
    val jsonElement = Json.parseToJsonElement(json)

    val topicId = deserializeTopicId(jsonElement.jsonObject["TopicId"]!!.toString())
    val entry = deserializeEntry(jsonElement.jsonObject["Entry"]!!.toString())

    return CondensedTopic(
        topicId=topicId,
        entry=entry
    )
}

fun deserializeTopicId(json: String): TopicId {
    val jsonElement = Json.parseToJsonElement(json)

    val id = jsonElement.jsonObject["Id"]!!.jsonPrimitive.int
    val topicTitle = deserializeTopicTitle(jsonElement.jsonObject["TopicTitle"]!!.toString())
    val title = jsonElement.jsonObject["Title"]!!.jsonPrimitive.content

    return TopicId(
        id=id,
        topicTitle=topicTitle,
        title=title
    )
}

fun deserializeTopicTitle(json: String): TopicTitle {
    val jsonElement = Json.parseToJsonElement(json)

    val title = jsonElement.jsonObject["Title"]!!.jsonPrimitive.content
    val kind = jsonElement.jsonObject["Kind"]?.jsonPrimitive?.contentOrNull

    return TopicTitle(
        title=title,
        kind=kind
    )
}
