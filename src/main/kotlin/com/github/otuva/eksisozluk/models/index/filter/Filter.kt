package com.github.otuva.eksisozluk.models.index.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Filter(
    @SerialName("ChannelId") val channelId: Int,
    @SerialName("ChannelName") val channelName: ChannelName,
    @SerialName("Enabled") val enabled: Boolean,
)
