package com.github.otuva.eksisozluk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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