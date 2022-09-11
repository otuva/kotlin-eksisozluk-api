package com.github.otuva.eksisozluk.models

import kotlinx.serialization.json.*

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
data class UserEntries(
    val pinnedEntry: Topic?,
    val entries: List<CondensedTopic>,
    val pageCount: Int,
    val pageSize: Int,
    val pageIndex: Int
)

/**
 * Represent an entry page shown in the users profile page. Note that this is not the same as [Topic]
 *
 * @param topicId the id - title pair of the topic
 * @param entry actual entry in the aforementioned topic
 * */
data class CondensedTopic(
    val topicId: TopicId,
    val entry: Entry
)

/**
 * Represents id - title pair of a topic.
 *
 * @param id the id of the topic
 * @param topicTitle the title - kind of the topic
 * @param title the title of the topic
 * */
data class TopicId(
    val id: Int,
    val topicTitle: TopicTitle,
    val title: String
)

/**
 * Represents topic - kind pair.
 *
 * @param title the title of the topic
 * @param kind the kind of the topic
 * */
data class TopicTitle(
    val title: String,
    val kind: String?
)

/**
 * Parses a JSON string to an instance of [UserEntries].
 *
 * @param json the JSON string to parse
 *
 * @return an instance of [UserEntries]
 * */
fun deserializeUserEntries(json: String): UserEntries {
    val jsonElement = Json.parseToJsonElement(json)

    val pinnedEntry =
        if (jsonElement.jsonObject["PinnedEntry"] != null && jsonElement.jsonObject["PinnedEntry"] != JsonNull) deserializeTopic(
            jsonElement.jsonObject["PinnedEntry"].toString()
        ) else null
    val entries = jsonElement.jsonObject["Entries"]!!.jsonArray.map { deserializeCondensedTopic(it.toString()) }
    val pageCount = jsonElement.jsonObject["PageCount"]!!.jsonPrimitive.int
    val pageSize = jsonElement.jsonObject["PageSize"]!!.jsonPrimitive.int
    val pageIndex = jsonElement.jsonObject["PageIndex"]!!.jsonPrimitive.int

    return UserEntries(
        pinnedEntry = pinnedEntry,
        entries = entries,
        pageCount = pageCount,
        pageSize = pageSize,
        pageIndex = pageIndex
    )
}

/**
 * Parses a JSON string to an instance of [CondensedTopic].
 *
 * @param json the JSON string to parse
 *
 * @return an instance of [CondensedTopic]
 * */
fun deserializeCondensedTopic(json: String): CondensedTopic {
    val jsonElement = Json.parseToJsonElement(json)

    val topicId = deserializeTopicId(jsonElement.jsonObject["TopicId"]!!.toString())
    val entry = deserializeEntry(jsonElement.jsonObject["Entry"]!!.toString())

    return CondensedTopic(
        topicId = topicId,
        entry = entry
    )
}

/**
 * Parses a JSON string to an instance of [TopicId].
 *
 * @param json the JSON string to parse
 *
 * @return an instance of [TopicId]
 * */
fun deserializeTopicId(json: String): TopicId {
    val jsonElement = Json.parseToJsonElement(json)

    val id = jsonElement.jsonObject["Id"]!!.jsonPrimitive.int
    val topicTitle = deserializeTopicTitle(jsonElement.jsonObject["TopicTitle"]!!.toString())
    val title = jsonElement.jsonObject["Title"]!!.jsonPrimitive.content

    return TopicId(
        id = id,
        topicTitle = topicTitle,
        title = title
    )
}

/**
 * Parses a JSON string to an instance of [TopicTitle].
 *
 * @param json the JSON string to parse
 *
 * @return an instance of [TopicTitle]
 * */
fun deserializeTopicTitle(json: String): TopicTitle {
    val jsonElement = Json.parseToJsonElement(json)

    val title = jsonElement.jsonObject["Title"]!!.jsonPrimitive.content
    val kind = jsonElement.jsonObject["Kind"]?.jsonPrimitive?.contentOrNull

    return TopicTitle(
        title = title,
        kind = kind
    )
}
