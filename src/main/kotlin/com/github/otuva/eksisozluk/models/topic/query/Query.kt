package com.github.otuva.eksisozluk.models.topic.query

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Query(
    @SerialName("Type") val type: Type,
    @SerialName("QueryData") val queryData: QueryData,
    @SerialName("RedirectedFrom") val redirectedFrom: String,
)
