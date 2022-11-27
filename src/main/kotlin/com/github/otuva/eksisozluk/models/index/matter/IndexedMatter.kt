package com.github.otuva.eksisozluk.models.index.matter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single matter in the index page.
 *
 * @param matchedCount How many new entries are there in the matter.
 * Used for displaying the number along with the matter in the index page.
 * @param id The id of the matter. Call function with this.
 * @param fullCount Total number of entries in the matter. Not used for now and is always 0.
 * @param detail String related to that matter. Not used in the index page and is always null.
 * @param matterTitle The title of the matter.
 * @param title The title of the topic which the matter belongs to.
 * */
@Serializable
public data class IndexedMatter(
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("Id") val id: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Detail") val detail: String?,
    @SerialName("MatterTitle") val matterTitle: String,
    @SerialName("Title") val title: String,
)
