package io.github.otuva.eksisozluk.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

fun deserializeEntryCounts(response: String): EntryCounts {
    val jsonElement = Json.parseToJsonElement(response)

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

@Serializable
data class EntryCounts(
    val beforeFirstEntry: Int,
    val afterLastEntry: Int,
    val buddy: Int,
    val total: Int
)