package com.github.otuva.eksisozluk.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a badge name
 *
 * @param value Badge name
 * */
@Serializable
public enum class BadgeName(private val value: String) {
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