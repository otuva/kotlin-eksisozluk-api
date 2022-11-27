package com.github.otuva.eksisozluk.models.index.debe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents debe itself.
 *
 * @param title What debe stands for ("dünün en beğenilen entry'leri")
 * @param description Description of debe ("dün okuyanlardan şukela'yı kapmış, onları kah güldürmüş, kah 'doğru lan' dedirtmiş entry'ler.")
 * @param debeItems Topics in debe.
 * */
@Serializable
public data class Debe(
    @SerialName("Title") val title: String,
    @SerialName("Description") val description: String,
    @SerialName("DebeItems") val debeItems: List<DebeItem>
)
