package com.github.otuva.eksisozluk.models.user.badge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a text badge name
 * */
@Serializable
public enum class BadgeName{
    @SerialName("karagöz")
    Karagoz,

    @SerialName("kayıp")
    Kayip,

    @SerialName("lanetli")
    Lanetli,

    @SerialName("leyla")
    Leyla,

    @SerialName("çaylak")
    Caylak,

    @SerialName("merhum")
    Merhum,

//    @SerialName("cpu")
//    Cpu,

//    @SerialName("editör")
//    Editor,

//    @SerialName("ekşi")
//    Eksi,
}