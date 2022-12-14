package com.github.otuva.eksisozluk.models.topic

/**
 * Represents a sorting & filtering option for a topic.
 *
 * [All] all entries. this is the default for searched topics | 'tumu'
 *
 * [Best] best entries all time | 'sukela tumu'
 *
 * [BestToday] best entries today | 'sukela bugun'
 *
 * [Hot] entries seen on the popular page. this is the default if navigated from popular index | 'gundem'
 *
 * ↓-----------------requires-login---------------↓
 *
 * [Following] entries from users you follow | 'takip ettiklerim'
 *
 * [Links] entries with links | 'linkler'
 *
 * [Images] entries with images | 'gorseller'
 *
 * [EksiSeyler] entries included in eksi seyler | "eksi seyler'de"
 * */
public enum class FilterType(public val value: String) {
    Best("allnice"),
    BestToday("dailynice"),
    Hot("popular"),
    All(""),
    Following("buddies"),
    Links("links"),
    Images("images"),
    EksiSeyler("eksiseyler")
}