package com.github.otuva.eksisozluk.models.index.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ChannelName(private val value: String) {
    @SerialName("spor")
    Spor("spor"),

    @SerialName("siyaset")
    Siyaset("siyaset"),

    @SerialName("anket")
    Anket("anket"),

    @SerialName("ilişkiler")
    Iliskiler("ilişkiler"),

    @SerialName("ekşi-sözlük")
    EksiSozluk("ekşi-sözlük"),

    @SerialName("yetişkin")
    Yetiskin("yetişkin"),

    @SerialName("troll")
    Troll("troll"),
}
