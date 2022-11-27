package com.github.otuva.eksisozluk.models.index.olay

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single followed matter.
 * Ramified from [OlayResult]
 *
 * @param snapshot The id of the first entry of the matter since the last time it was checked.
 * This is what you call the getSnapshot function with when user clicks on the matter.
 * @param matchedCount The number of entries that match the matter since the last time it was checked.
 * This would be displayed along with the matter name.
 * @param id The id of the matter.
 * @param fullCount Total number of entries in the matter.
 * @param detail Details regarding the matter. Not used and is always null.
 * @param matterTitle The title of the matter.
 * @param title The title of the topic that the matter belongs to.
 * */
@Serializable
@SerialName("m")
public data class Matter(
    @SerialName("Snapshot") val snapshot: Int,
    @SerialName("MatchedCount") val matchedCount: Int,
    @SerialName("Id") val id: Int,
    @SerialName("FullCount") val fullCount: Int,
    @SerialName("Detail") val detail: String?,
    @SerialName("MatterTitle") val matterTitle: String,
    @SerialName("Title") val title: String,
): OlayResult()
