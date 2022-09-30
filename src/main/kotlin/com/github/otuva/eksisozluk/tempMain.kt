package com.github.otuva.eksisozluk

/**
 * temporary main function for testing
 * this will be removed in the future
 * */
public suspend fun main() {
    val eksiClient = EksiClient()

    eksiClient.debugUseSessionFromFile()
//    eksiClient.buildClient()

    // 32132

    val testing = eksiClient.getEntry(1)
//    val testing = eksiClient.getChannelFilters()
//    val testing = eksiClient.setChannelFilters(eksiClient.createFilters(enableIliskiler = false, enableSpor = false))

//    eksiClient.likeEntry(1)
//    val testing = eksiClient.getTopic(31795, SortingType.Images)
//    val testing1 = eksiClient.getIndexToday()
//    val testing = eksiClient.debugGetResponse(Routes.BaseUrl.path + routes["indexGetFilterChannels"]!!, "POST")

    println(testing)
//    println(testing1)
}