package com.github.otuva.eksisozluk.utils

import com.github.otuva.eksisozluk.models.index.filter.ChannelName
import com.github.otuva.eksisozluk.models.index.filter.Filter

public const val apiSecret: String = "68f779c5-4d39-411a-bd12-cbcc50dc83dd"

/**
 * Create filters list for index popular function.
 * By default, everything is enabled, and you can disable them by setting their respective parameters to false.
 *
 * For example if you wanted to disable 'iliskiler' listings, you can set [enableIliskiler] to false.
 * */
public fun createFilters(
    enableSpor: Boolean = true,
    enableSiyaset: Boolean = true,
    enableAnket: Boolean = true,
    enableIliskiler: Boolean = true,
    enableEksiSozluk: Boolean = true,
    enableYetiskin: Boolean = true,
    enableTroll: Boolean = true
): List<Filter> {
    return listOf(
        Filter(1, ChannelName.Spor, enableSpor),
        Filter(2, ChannelName.Siyaset, enableSiyaset),
        Filter(4, ChannelName.Anket, enableAnket),
        Filter(5, ChannelName.Iliskiler, enableIliskiler),
        Filter(10, ChannelName.EksiSozluk, enableEksiSozluk),
        Filter(11, ChannelName.Yetiskin, enableYetiskin),
        Filter(39, ChannelName.Troll, enableTroll),
    )
}