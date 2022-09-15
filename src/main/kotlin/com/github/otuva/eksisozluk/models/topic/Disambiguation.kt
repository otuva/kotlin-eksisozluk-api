package com.github.otuva.eksisozluk.models.topic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents disambiguation in a topic.
 * In order to handle redirections title and kind of the disambiguation is used respectively instead of topic id.
 * Because api doesn't return id of the disambiguation.
 *
 * @param title The title of the disambiguation.
 * @param kind The kind of the disambiguation. Could be 'sozluk yazari', 'dizi', 'oyun' etc.
 *
 *  TODO: add disambiguation handler function then add see annotation
 * */
@Serializable
data class Disambiguation(
    @SerialName("Title") val title: String,
    @SerialName("Kind") val kind: String
)