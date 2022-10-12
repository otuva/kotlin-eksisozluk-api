package com.github.otuva.eksisozluk.models.user.badge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single badge
 *
 * @param id Badge internal id
 * @param name Badge name
 * @param description Badge description
 * @param imageUrl Badge image url
 * @param displayOrder Badge display order on user profile. Index starts from 1 and goes up to 4. 99 means the badge is not displayed on user profile.
 * @param owned <Useless> | Unknown. It is always false for all badges, and it's useless because the API does not return unowned badges.
 * @param selected Unknown. It is always false for all badges.
 * @param showInactive Unknown
 * */
@Serializable
public data class ImageBadge(
    @SerialName("Id") val id: Int,
    @SerialName("Name") val name: ImageBadgeName,
    @SerialName("Description") val description: String,
    @SerialName("ImageUrl") val imageUrl: String?,
    @SerialName("DisplayOrder") val displayOrder: Int,
    @SerialName("Owned") val owned: Boolean,
    @SerialName("Selected") val selected: Boolean,
    @SerialName("ShowInactive") val showInactive: Boolean
)