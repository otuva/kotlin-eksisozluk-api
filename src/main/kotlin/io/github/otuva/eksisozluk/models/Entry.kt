package io.github.otuva.eksisozluk.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

fun deserializeEntry(response: String): Entry {
    val jsonElement = Json.parseToJsonElement(response)

    val id = jsonElement.jsonObject["Id"]!!.jsonPrimitive.int
    val content = jsonElement.jsonObject["Content"]!!.jsonPrimitive.content
    val author = deserializeUserIdentifier(jsonElement.jsonObject["Author"].toString())
    val created = LocalDateTime.parse(jsonElement.jsonObject["Created"]!!.jsonPrimitive.content)
    val isFavorite = jsonElement.jsonObject["IsFavorite"]!!.jsonPrimitive.boolean
    val favoriteCount = jsonElement.jsonObject["FavoriteCount"]!!.jsonPrimitive.int
    val hidden = jsonElement.jsonObject["Hidden"]!!.jsonPrimitive.boolean
    val active = jsonElement.jsonObject["Active"]!!.jsonPrimitive.boolean
    val commentCount = jsonElement.jsonObject["CommentCount"]!!.jsonPrimitive.int
    val commentSummary = jsonElement.jsonObject["CommentSummary"]?.jsonPrimitive?.content
    val lastUpdated = if (jsonElement.jsonObject["LastUpdated"] != JsonNull) LocalDateTime.parse(jsonElement.jsonObject["LastUpdated"]!!.jsonPrimitive.content) else null
    val avatarUrl = jsonElement.jsonObject["AvatarUrl"]?.jsonPrimitive?.content
    // convert media jsonarray to list of string
    val media = if (jsonElement.jsonObject["Media"] != JsonNull) jsonElement.jsonObject["Media"]?.jsonArray!!.toList().map { it.jsonPrimitive.content } else null
    val isSponsored = jsonElement.jsonObject["IsSponsored"]!!.jsonPrimitive.boolean
    val isPinned = jsonElement.jsonObject["IsPinned"]!!.jsonPrimitive.boolean
    val isPinnedOnProfile = jsonElement.jsonObject["IsPinnedOnProfile"]!!.jsonPrimitive.boolean
    val isVerifiedAccount = jsonElement.jsonObject["IsVerifiedAccount"]!!.jsonPrimitive.boolean

    return Entry(
        id=id,
        content=content,
        author=author,
        created=created,
        isFavorite=isFavorite,
        favoriteCount=favoriteCount,
        hidden=hidden,
        active=active,
        commentCount=commentCount,
        commentSummary=commentSummary,
        lastUpdated=lastUpdated,
        avatarUrl=avatarUrl,
        media=media,
        isSponsored=isSponsored,
        isPinned=isPinned,
        isPinnedOnProfile=isPinnedOnProfile,
        isVerifiedAccount=isVerifiedAccount
    )
}

@Serializable
data class Entry(
    val id: Int,
    val content: String,
    val author: UserIdentifier,
    val created: LocalDateTime,
    val isFavorite: Boolean,
    val favoriteCount: Int,
    val hidden: Boolean,
    val active: Boolean,
    val commentCount: Int,
    val commentSummary: String? = null,
    val lastUpdated: LocalDateTime? = null,
    val avatarUrl: String? = null,
    val media: List<String>? = null,
    val isSponsored: Boolean,
    val isPinned: Boolean,
    val isPinnedOnProfile: Boolean,
    val isVerifiedAccount: Boolean,
)