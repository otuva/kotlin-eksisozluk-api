package com.github.otuva.eksisozluk.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user
 *
 * @param userInfo User information represented by [UserInfo]
 * @param badges User badges. Note that api returns an empty list unless user is 'caylak' or 'leyla' etc.
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
    @SerialName("UserInfo") val userInfo: UserInfo,
    @SerialName("Badges") val badges: List<Badge>,
    @SerialName("HasEntryUsedOnSeyler") val hasEntryUsedOnSeyler: Boolean,
    @SerialName("FollowerCount") val followerCount: Int,
    @SerialName("FollowingsCount") val followingsCount: Int,
    @SerialName("Picture") val picture: String?,
    @SerialName("PinnedEntry") val pinnedEntry: Topic?
)

/**
 * Represent a nick-id pair of a user
 *
 * @param nick User's nick
 * @param id User's internal id
 * */
@Serializable
data class UserIdentifier(
    @SerialName("Nick") val nick: String,
    @SerialName("Id") val id: Int
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
    @SerialName("UserIdentifier") val userIdentifier: UserIdentifier,
    @SerialName("RemainingInvitation") val remainingInvitation: Int,
    @SerialName("TwitterScreenName") val twitterScreenName: String?,
    @SerialName("FacebookProfileUrl") val facebookProfileUrl: String?,
    @SerialName("FacebookScreenName") val facebookScreenName: String?,
    @SerialName("InstagramScreenName") val instagramScreenName: String?,
    @SerialName("InstagramProfileUrl") val instagramProfileUrl: String?,
    @SerialName("Karma") val karma: Karma?,
    @SerialName("EntryCounts") val entryCounts: UserEntryCounts,
    @SerialName("LastEntryDate") val lastEntryDate: LocalDateTime?,
    @SerialName("StandingQueueNumber") val standingQueueNumber: Int,
    @SerialName("HasAnyPendingInvitation") val hasAnyPendingInvitation: Boolean,
    @SerialName("IsBuddy") val isBuddy: Boolean,
    @SerialName("IsBlocked") val isBlocked: Boolean,
    @SerialName("IsFollowed") val isFollowed: Boolean,
    @SerialName("IsCorporate") val isCorporate: Boolean,
    @SerialName("IsDeactivated") val isDeactivated: Boolean,
    @SerialName("IsKarmaShown") val isKarmaShown: Boolean,
    @SerialName("IsCaylak") val isCaylak: Boolean,
    @SerialName("IsIndexTitlesBlocked") val isIndexTitlesBlocked: Boolean,
    @SerialName("Note") val note: String?,
    @SerialName("CursePeriod") val cursePeriod: CursePeriod?,
    @SerialName("IsCursed") val isCursed: Boolean,
    @SerialName("IsBanned") val isBanned: Boolean,
    @SerialName("DisplayTwitterProfile") val displayTwitterProfile: Boolean,
    @SerialName("DisplayFacebookProfile") val displayFacebookProfile: Boolean,
    @SerialName("DisplayInstagramProfile") val displayInstagramProfile: Boolean,
)

/**
 * Represents a time period of the curse
 *
 * @param from Curse start date
 * @param to Curse end date
 * */
@Serializable
data class CursePeriod(
    @SerialName("From") val from: LocalDateTime,
    @SerialName("To") val to: LocalDateTime
)

/**
 * Represents a user's entry statistics
 *
 * @param total Total number of entries
 * @param lastMonth Number of entries written in the last month
 * @param lastWeek Number of entries written in the last week
 * @param today Number of entries written today
 * */
@Serializable
data class UserEntryCounts(
    @SerialName("Total") val total: Int,
    @SerialName("LastMonth") val lastMonth: Int,
    @SerialName("LastWeek") val lastWeek: Int,
    @SerialName("Today") val today: Int
)

/**
 * Represents a user's karma
 *
 * @param name Karma name represented by [KarmaName]
 * @param value Karma value
 *
 * @see [KarmaName]
 * */
@Serializable
data class Karma(
    @SerialName("Name") val name: KarmaName,
    @SerialName("Value") val value: Int
)

/**
 * Represents a single badge
 *
 * @param id Badge id. This is not fixed and can change
 * @param name Badge name
 * @param description Badge description. It is an empty string for most badges
 * @param imageUrl <Useless> | Badge image url. It is null for all badges
 * @param displayOrder <Useless> | Badge display order. It is 99 for all badges
 * @param owned <Useless> | Unknown. It is always false for all badges
 * @param selected <Useless> | Unknown. It is always false for all badges. Could be whether user chose to display on profile
 * @param showInactive <Useless> | Unknown. It is always false for all badges
 * */
@Serializable
data class Badge(
    @SerialName("Id") val id: Int,
    @SerialName("Name") val name: BadgeName,
    @SerialName("Description") val description: String,
    @SerialName("ImageUrl") val imageUrl: String?,
    @SerialName("DisplayOrder") val displayOrder: Int,
    @SerialName("Owned") val owned: Boolean,
    @SerialName("Selected") val selected: Boolean,
    @SerialName("ShowInactive") val showInactive: Boolean
)

/**
 * Represents a user's karma name
 *
 * @param value Karma name
 * */
@Serializable
enum class KarmaName(val value: String) {
    @SerialName("aklıselim")
    Akliselim("aklıselim"),
    @SerialName("anadolu çocuğu")
    AnadoluCocugu("anadolu çocuğu"),
    @SerialName("anarşist")
    Anarsist("anarşist"),
    @SerialName("ağır abi")
    AgirAbi("ağır abi"),
    @SerialName("baldan tatlı")
    BaldanTatli("baldan tatlı"),
    @SerialName("bal küpü")
    BalKupu("bal küpü"),
    @SerialName("battal gazi")
    BattalGazi("battal gazi"),
    @SerialName("bıçkın")
    Bickin("bıçkın"),
    @SerialName("delikanlı")
    Delikanli("delikanlı"),
    @SerialName("düz adam")
    DuzAdam("düz adam"),
    @SerialName("energizer tavşanı")
    EnergizerTavsani("energizer tavşanı"),
    @SerialName("gençlerin sevgilisi")
    GenclerinSevgilisi("gençlerin sevgilisi"),
    @SerialName("her eve lazım")
    HerEveLazim("her eve lazım"),
    @SerialName("hippi")
    Hippi("hippi"),
    @SerialName("hırçın golcü")
    HircinGolcu("hırçın golcü"),
    @SerialName("iç güveysinden hallice")
    IcGuveysindenHallice("iç güveysinden hallice"),
    @SerialName("kaotik nötral")
    KaotikNotral("kaotik nötral"),
    @SerialName("kızgın kumlardan serin sulara")
    KizginKumlardanSerinSulara("kızgın kumlardan serin sulara"),
    @SerialName("kofti anarşist")
    KoftiAnarsist("kofti anarşist"),
    @SerialName("leziz")
    Leziz("leziz"),
    @SerialName("mangal yürekli rişar")
    MangalYurekliRisar("mangal yürekli rişar"),
    @SerialName("minyonların sevgilisi")
    MinyonlarinSevgilisi("minyonların sevgilisi"),
    @SerialName("mülayim ama sempatik")
    MulayimAmaSempatik("mülayim ama sempatik"),
    @SerialName("müzmin yedek")
    MuzminYedek("müzmin yedek"),
    @SerialName("padawan")
    Padawan("padawan"),
    @SerialName("prezentabl")
    Prezentabl("prezentabl"),
    @SerialName("rating canavarı")
    RatingCanavari("rating canavarı"),
    @SerialName("tadına doyum olmaz")
    TadinaDoyumOlmaz("tadına doyum olmaz"),
    @SerialName("çetrefilli")
    Cetrefilli("çetrefilli"),
    @SerialName("çılgın")
    Cilgin("çılgın"),
    @SerialName("şamda kayısı")
    SamdaKayisi("şamda kayısı"),
    @SerialName("şekerpare")
    Sekerpare("şekerpare"),
    @SerialName("şeker abi")
    SekerAbi("şeker abi"),
}

/**
 * Represents a badge name
 *
 * @param value Badge name
 * */
@Serializable
enum class BadgeName(val value: String) {
    @SerialName("azimli")
    Azimli("azimli"),
    @SerialName("bot")
    Bot("bot"),
    @SerialName("cizreli")
    Cizreli("cizreli"),
    @SerialName("cpu")
    Cpu("cpu"),
    @SerialName("editör")
    Editor("editör"),
    @SerialName("ekşi")
    Eksi("ekşi"),
    @SerialName("ekşiteker")
    EksiTeker("ekşiteker"),
    @SerialName("emekli")
    Emekli("emekli"),
    @SerialName("gudik")
    Gudik("gudik"),
    @SerialName("itfaiyeci")
    Itfaiyeci("itfaiyeci"),
    @SerialName("karagöz")
    Karagoz("karagöz"),
    @SerialName("kayıp")
    Kayip("kayıp"),
    @SerialName("lanetli")
    Lanetli("lanetli"),
    @SerialName("leyla")
    Leyla("leyla"),
    @SerialName("merhum")
    Merhum("merhum"),
    @SerialName("mücahit")
    Mucahit("mücahit"),
    @SerialName("organizatör")
    Organizator("organizatör"),
    @SerialName("ormancı")
    Ormanci("ormancı"),
    @SerialName("preator")
    Preator("preator"),
    @SerialName("scener")
    Scener("scener"),
    @SerialName("tasnifçi")
    Tasnifci("tasnifçi"),
    @SerialName("64-bit")
    The64Bit("64-bit"),
    @SerialName("çaylak")
    Caylak("çaylak")
}