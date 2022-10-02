package com.github.otuva.eksisozluk.models.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SortOrder(public val value: Int) {
    @SerialName("0") Alphabetical(0),
    @SerialName("1") ReverseChronological(1),
    @SerialName("2") Popularity(2),
}