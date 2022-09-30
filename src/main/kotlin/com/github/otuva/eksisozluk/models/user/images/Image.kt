package com.github.otuva.eksisozluk.models.user.images

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Image(
    @SerialName("ImageKey") val imageKey: String,
    @SerialName("Extension") val extension: String,
    @SerialName("Path") val path: String,
    @SerialName("IsDeleted") val isDeleted: Boolean,
    @SerialName("CdnUrl") val cdnUrl: String,
    @SerialName("ThumbnailUrl") val thumbnailUrl: String
)
