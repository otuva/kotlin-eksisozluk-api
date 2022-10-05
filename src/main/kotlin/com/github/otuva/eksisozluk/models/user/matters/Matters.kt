package com.github.otuva.eksisozluk.models.user.matters

import com.github.otuva.eksisozluk.models.matter.MatterInfo
import com.github.otuva.eksisozluk.models.user.UserIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Matters(
    @SerialName("PageCount") val pageCount: Int,
    @SerialName("PageSize") val pageSize: Int,
    @SerialName("PageIndex") val pageIndex: Int,
    @SerialName("RowCount") val rowCount: Int,
    @SerialName("Matters") val matters: List<MatterInfo>,
    @SerialName("StatOwner") val statOwner: UserIdentifier,
    @SerialName("Caption") val caption: String
)