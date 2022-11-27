package com.github.otuva.eksisozluk.models.index.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This class will hold all the filters for the index page. Used to send a request to the server.
 * */
@Serializable
public data class Filters(
    @SerialName("Filters") val filters: List<Filter>
)
