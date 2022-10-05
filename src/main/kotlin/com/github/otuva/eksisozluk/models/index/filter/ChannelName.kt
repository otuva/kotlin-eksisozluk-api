package com.github.otuva.eksisozluk.models.index.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ChannelName {
    @SerialName("spor")
    Spor,

    @SerialName("siyaset")
    Siyaset,

    @SerialName("anket")
    Anket,

    @SerialName("ilişkiler")
    Iliskiler,

    @SerialName("ekşi-sözlük")
    EksiSozluk,

    @SerialName("yetişkin")
    Yetiskin,

    @SerialName("troll")
    Troll,
}
