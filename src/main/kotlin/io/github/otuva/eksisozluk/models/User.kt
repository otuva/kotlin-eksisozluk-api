package io.github.otuva.eksisozluk.models

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.*

/**
 * Represents a user
 * @param userInfo User information
 * @param badges User badges. Note that api returns an empty list
 * @param hasEntryUsedOnSeyler Whether the user has an entry used on 'EksiSeyler'
 * @param followerCount Number of followers
 * @param followingsCount Number of users the user is following
 * @param picture Link to the user's profile picture
 * @param pinnedEntry This parameter is useless. It is always null. To see pinned entry use user's last entries
 *
 * @see UserInfo
 * */
@Serializable
data class User(
    val userInfo: UserInfo,
    val badges: List<Badge>,
    val hasEntryUsedOnSeyler: Boolean,
    val followerCount: Int,
    val followingsCount: Int,
    val picture: String?,
    val pinnedEntry: Entry?
)

/**
 * Represent a nick-id pair of a user
 * @param nick User's nick
 * @param id User's internal id
 * */
@Serializable
data class UserIdentifier(
    val nick: String,
    val id: Int
)

/**
 * Represents a user's information
 * @param userIdentifier User's nick-id pair
 * @param remainingInvitation undocumented/unknown. could be a moderator's invitation count
 * @param twitterScreenName User's twitter handle
 * @param facebookProfileUrl User's facebook profile url
 * @param facebookScreenName User's facebook screen name
 * @param instagramScreenName User's instagram screen name
 * @param instagramProfileUrl User's instagram profile url
 * @param karma User's karma shown on the profile page
 * @param entryCounts User's entry counts by time intervals
 * @param lastEntryDate User's last written entry date
 * @param standingQueueNumber User's standing queue number note that this will be 0 for authors
 *
 * */
@Serializable
data class UserInfo(
    val userIdentifier: UserIdentifier,
    val remainingInvitation: Int,
    val twitterScreenName: String?,
    val facebookProfileUrl: String?,
    val facebookScreenName: String?,
    val instagramScreenName: String?,
    val instagramProfileUrl: String?,
    val karma: Karma?,
    val entryCounts: UserEntryCounts,
    val lastEntryDate: LocalDateTime?,
    val standingQueueNumber: Int,
    val hasAnyPendingInvitation: Boolean,
    val isBuddy: Boolean,
    val isBlocked: Boolean,
    val isFollowed: Boolean,
    val isCorporate: Boolean,
    val isDeactivated: Boolean,
    val isKarmaShown: Boolean,
    val isCaylak: Boolean,
    val isIndexTitlesBlocked: Boolean,
    val note: String?,
    val cursePeriod: CursePeriod?,
    val isCursed: Boolean,
    val isBanned: Boolean,
    val displayTwitterProfile: Boolean,
    val displayFacebookProfile: Boolean,
    val displayInstagramProfile: Boolean,
)

@Serializable
data class CursePeriod(
    val from: LocalDateTime,
    val to: LocalDateTime
)

@Serializable
data class UserEntryCounts(
    val total: Int,
    val lastMonth: Int,
    val lastWeek: Int,
    val today: Int
)

@Serializable
data class Karma(
    val name: KarmaName,
    val value: Int
)

@Serializable
data class Badge(
    val id: Int,
    val name: BadgeName,
    val description: String?,
    val imageUrl: String?,
    val displayOrder: Int,
    val owned: Boolean,
    val selected: Boolean,
    val showInactive: Boolean
)

enum class KarmaName(val value: String) {
    Akliselim("aklıselim"),
    AnadoluCocugu("anadolu çocuğu"),
    Anarsist("anarşist"),
    AgirAbi("ağır abi"),
    BaldanTatli("baldan tatlı"),
    BalKupu("bal küpü"),
    BattalGazi("battal gazi"),
    Bickin("bıçkın"),
    Delikanli("delikanlı"),
    DuzAdam("düz adam"),
    EnergizerTavsani("energizer tavşanı"),
    GenclerinSevgilisi("gençlerin sevgilisi"),
    HerEveLazim("her eve lazım"),
    Hippi("hippi"),
    HircinGolcu("hırçın golcü"),
    IcGuveysindenHallice("iç güveysinden hallice"),
    KaotikNotral("kaotik nötral"),
    KizginKumlardanSerinSulara("kızgın kumlardan serin sulara"),
    KoftiAnarsist("kofti anarşist"),
    Leziz("leziz"),
    MangalYurekliRisar("mangal yürekli rişar"),
    MinyonlarinSevgilisi("minyonların sevgilisi"),
    MulayimAmaSempatik("mülayim ama sempatik"),
    MuzminYedek("müzmin yedek"),
    Padawan("padawan"),
    Prezentabl("prezentabl"),
    RatingCanavari("rating canavarı"),
    TadinaDoyumOlmaz("tadına doyum olmaz"),
    Cetrefilli("çetrefilli"),
    Cilgin("çılgın"),
    SamdaKayisi("şamda kayısı"),
    Sekerpare("şekerpare"),
    SekerAbi("şeker abi"),
}

enum class BadgeName(val value: String) {
    Azimli("azimli"),
    Bot("bot"),
    Cizreli("cizreli"),
    Cpu("cpu"),
    Editor("editör"),
    Eksi("ekşi"),
    EksiTeker("ekşiteker"),
    Emekli("emekli"),
    Gudik("gudik"),
    Itfaiyeci("itfaiyeci"),
    Karagoz("karagöz"),
    Kayip("kayıp"),
    Lanetli("lanetli"),
    Leyla("leyla"),
    Merhum("merhum"),
    Mucahit("mücahit"),
    Organizator("organizatör"),
    Ormanci("ormancı"),
    Preator("preator"),
    Scener("scener"),
    Tasnifci("tasnifçi"),
    The64Bit("64-bit"),
    Caylak("çaylak")
}

fun deserializeUser(json: String): User {
    val jsonElement = Json.parseToJsonElement(json)

    val userInfo = deserializeUserInfo(jsonElement.jsonObject["UserInfo"].toString())
    val badges = jsonElement.jsonObject["Badges"]!!.jsonArray.map { deserializeBadge(it.toString()) }
    val hasEntryUsedOnSeyler = jsonElement.jsonObject["HasEntryUsedOnSeyler"]!!.jsonPrimitive.boolean
    val followerCount = jsonElement.jsonObject["FollowerCount"]!!.jsonPrimitive.int
    val followingsCount = jsonElement.jsonObject["FollowingsCount"]!!.jsonPrimitive.int
    val picture = jsonElement.jsonObject["Picture"]?.jsonPrimitive?.contentOrNull
    val pinnedEntry = if (jsonElement.jsonObject["PinnedEntry"] != JsonNull) deserializeEntry(jsonElement.jsonObject["PinnedEntry"].toString()) else null

    return User(
        userInfo = userInfo,
        badges = badges,
        hasEntryUsedOnSeyler = hasEntryUsedOnSeyler,
        followerCount = followerCount,
        followingsCount = followingsCount,
        picture = picture,
        pinnedEntry = pinnedEntry
    )
}

fun deserializeUserIdentifier(json: String): UserIdentifier {
    val jsonElement = Json.parseToJsonElement(json)

    val nick = jsonElement.jsonObject["Nick"]!!.jsonPrimitive.content
    val id = jsonElement.jsonObject["Id"]!!.jsonPrimitive.int

    return UserIdentifier(
        nick = nick,
        id = id
    )
}

fun deserializeUserInfo(json: String): UserInfo {
    val jsonElement = Json.parseToJsonElement(json)

    val userIdentifier = deserializeUserIdentifier(jsonElement.jsonObject["UserIdentifier"].toString())
    val remainingInvitation = jsonElement.jsonObject["RemainingInvitation"]!!.jsonPrimitive.int
    val twitterScreenName = jsonElement.jsonObject["TwitterScreenName"]?.jsonPrimitive?.contentOrNull
    val facebookProfileUrl = jsonElement.jsonObject["FacebookProfileUrl"]?.jsonPrimitive?.contentOrNull
    val facebookScreenName = jsonElement.jsonObject["FacebookScreenName"]?.jsonPrimitive?.contentOrNull
    val instagramScreenName = jsonElement.jsonObject["InstagramScreenName"]?.jsonPrimitive?.contentOrNull
    val instagramProfileUrl = jsonElement.jsonObject["InstagramProfileUrl"]?.jsonPrimitive?.contentOrNull
    val karma = if (jsonElement.jsonObject["Karma"] != JsonNull) deserializeKarma(jsonElement.jsonObject["Karma"].toString()) else null
    val entryCounts = deserializeUserEntryCounts(jsonElement.jsonObject["EntryCounts"].toString())
    val lastEntryDate = if (jsonElement.jsonObject["LastEntryDate"] != JsonNull) LocalDateTime.parse(jsonElement.jsonObject["LastEntryDate"]!!.jsonPrimitive.content) else null
    val standingQueueNumber = jsonElement.jsonObject["StandingQueueNumber"]!!.jsonPrimitive.int
    val hasAnyPendingInvitation = jsonElement.jsonObject["HasAnyPendingInvitation"]!!.jsonPrimitive.boolean
    val isBuddy = jsonElement.jsonObject["IsBuddy"]!!.jsonPrimitive.boolean
    val isBlocked = jsonElement.jsonObject["IsBlocked"]!!.jsonPrimitive.boolean
    val isFollowed = jsonElement.jsonObject["IsFollowed"]!!.jsonPrimitive.boolean
    val isCorporate = jsonElement.jsonObject["IsCorporate"]!!.jsonPrimitive.boolean
    val isDeactivated = jsonElement.jsonObject["IsDeactivated"]!!.jsonPrimitive.boolean
    val isKarmaShown = jsonElement.jsonObject["IsKarmaShown"]!!.jsonPrimitive.boolean
    val isCaylak = jsonElement.jsonObject["IsCaylak"]!!.jsonPrimitive.boolean
    val isIndexTitlesBlocked = jsonElement.jsonObject["IsIndexTitlesBlocked"]!!.jsonPrimitive.boolean
    val note = jsonElement.jsonObject["Note"]?.jsonPrimitive?.contentOrNull
    val cursePeriod = if (jsonElement.jsonObject["CursePeriod"] != JsonNull) deserializeCursePeriod(jsonElement.jsonObject["CursePeriod"].toString()) else null
    val isCursed = jsonElement.jsonObject["IsCursed"]!!.jsonPrimitive.boolean
    val isBanned = jsonElement.jsonObject["IsBanned"]!!.jsonPrimitive.boolean
    val displayTwitterProfile = jsonElement.jsonObject["DisplayTwitterProfile"]!!.jsonPrimitive.boolean
    val displayFacebookProfile = jsonElement.jsonObject["DisplayFacebookProfile"]!!.jsonPrimitive.boolean
    val displayInstagramProfile = jsonElement.jsonObject["DisplayInstagramProfile"]!!.jsonPrimitive.boolean

    return UserInfo(
        userIdentifier = userIdentifier,
        remainingInvitation = remainingInvitation,
        twitterScreenName = twitterScreenName,
        facebookProfileUrl = facebookProfileUrl,
        facebookScreenName = facebookScreenName,
        instagramScreenName = instagramScreenName,
        instagramProfileUrl = instagramProfileUrl,
        karma = karma,
        entryCounts = entryCounts,
        lastEntryDate = lastEntryDate,
        standingQueueNumber = standingQueueNumber,
        hasAnyPendingInvitation = hasAnyPendingInvitation,
        isBuddy = isBuddy,
        isBlocked = isBlocked,
        isFollowed = isFollowed,
        isCorporate = isCorporate,
        isDeactivated = isDeactivated,
        isKarmaShown = isKarmaShown,
        isCaylak = isCaylak,
        isIndexTitlesBlocked = isIndexTitlesBlocked,
        note = note,
        cursePeriod = cursePeriod,
        isCursed = isCursed,
        isBanned = isBanned,
        displayTwitterProfile = displayTwitterProfile,
        displayFacebookProfile = displayFacebookProfile,
        displayInstagramProfile = displayInstagramProfile
    )
}

fun deserializeCursePeriod(json: String): CursePeriod {
    val jsonElement = Json.parseToJsonElement(json)

    val from = LocalDateTime.parse(jsonElement.jsonObject["From"]!!.jsonPrimitive.content)
    val to = LocalDateTime.parse(jsonElement.jsonObject["To"]!!.jsonPrimitive.content)

    return CursePeriod(
        from = from,
        to = to
    )
}

fun deserializeUserEntryCounts(json: String): UserEntryCounts {
    val jsonElement = Json.parseToJsonElement(json)

    val total = jsonElement.jsonObject["Total"]!!.jsonPrimitive.int
    val lastMonth = jsonElement.jsonObject["LastMonth"]!!.jsonPrimitive.int
    val lastWeek = jsonElement.jsonObject["LastWeek"]!!.jsonPrimitive.int
    val today = jsonElement.jsonObject["Today"]!!.jsonPrimitive.int

    return UserEntryCounts(
        total = total,
        lastMonth = lastMonth,
        lastWeek = lastWeek,
        today = today
    )
}

fun deserializeKarma(json: String): Karma {
    val jsonElement = Json.parseToJsonElement(json)

    val rawKarmaName = jsonElement.jsonObject["Name"]!!.jsonPrimitive.content
    val karmaName = KarmaName.values().find { it.value == rawKarmaName }!!
    val value = jsonElement.jsonObject["Value"]!!.jsonPrimitive.int

    return Karma(
        name = karmaName,
        value = value
    )
}

fun deserializeBadge(json: String): Badge {
    val jsonElement = Json.parseToJsonElement(json)

    val id = jsonElement.jsonObject["Id"]!!.jsonPrimitive.int
    val name = BadgeName.values().find { it.value == jsonElement.jsonObject["Name"]!!.jsonPrimitive.content }!!
    val description = jsonElement.jsonObject["Description"]!!.jsonPrimitive.contentOrNull
    val imageUrl = jsonElement.jsonObject["ImageUrl"]!!.jsonPrimitive.content
    val displayOrder = jsonElement.jsonObject["DisplayOrder"]!!.jsonPrimitive.int
    val owned = jsonElement.jsonObject["Owned"]!!.jsonPrimitive.boolean
    val selected = jsonElement.jsonObject["Selected"]!!.jsonPrimitive.boolean
    val showInactive = jsonElement.jsonObject["ShowInactive"]!!.jsonPrimitive.boolean

    return Badge(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        displayOrder = displayOrder,
        owned = owned,
        selected = selected,
        showInactive = showInactive
    )
}