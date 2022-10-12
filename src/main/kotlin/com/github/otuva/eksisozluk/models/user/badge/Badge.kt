package com.github.otuva.eksisozluk.models.user.badge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single text badge
 *
 * @param id Badge id. This is not fixed and can change
 * @param name Badge name
 * @param description Badge description. It is an empty string for most badges
 * @param imageUrl <Useless> | Badge image url. It is null for all badges
 * @param displayOrder <Useless> | Badge display order. It is 99 for all badges
 * @param owned <Useless> | Unknown. It is always false for all badges
 * @param selected <Useless> | Unknown. It is always false for all badges. Could be whether user chose to display on profile
 * @param showInactive <Useless> | Unknown. It is always false for all badges
 * */
@Serializable
public data class Badge(
    @SerialName("Id") val id: Int,
    @SerialName("Name") val name: BadgeName,
    @SerialName("Description") val description: String,
    @SerialName("ImageUrl") val imageUrl: String?,
    @SerialName("DisplayOrder") val displayOrder: Int,
    @SerialName("Owned") val owned: Boolean,
    @SerialName("Selected") val selected: Boolean,
    @SerialName("ShowInactive") val showInactive: Boolean
)