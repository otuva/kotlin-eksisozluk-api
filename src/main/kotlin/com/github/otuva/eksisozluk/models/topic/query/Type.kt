package com.github.otuva.eksisozluk.models.topic.query

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class Type {
    @SerialName("fulltitle") FullTitle,
    @SerialName("missing") Missing,
}