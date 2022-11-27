package com.github.otuva.eksisozluk.models.index.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single filter for the index page.
 *
 * @param channelId The channel id of the filter.
 *
 * Spor = 1,
 *
 * Siyaset = 2,
 *
 * Anket = 4,
 *
 * Iliskiler = 5,
 *
 * EksiSozluk = 10,
 *
 * Yetiskin = 11,
 *
 * Troll = 39,
 *
 * @param channelName The [ChannelName] of the filter.
 * @param enabled Whether the filter is enabled or not.
 * */
@Serializable
public data class Filter(
    @SerialName("ChannelId") val channelId: Int,
    @SerialName("ChannelName") val channelName: ChannelName,
    @SerialName("Enabled") val enabled: Boolean,
)
