package com.github.otuva.eksisozluk.models.user.images

import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserImages(
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
    @SerialName("RowCount") val rowCount: Int,
    @SerialName("Images") val images: List<Image>,
    @SerialName("StatOwner") val statOwner: UserIdentifier,
    @SerialName("Caption") val caption: String,
)
