package com.github.otuva.eksisozluk.models.user.entries

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents topic - kind pair.
 *
 * @param title the title of the topic
 * @param kind the kind of the topic
 * */
@Serializable
public data class TopicTitle(
    @SerialName("Title") val title: String,
    @SerialName("Kind") val kind: String?
)