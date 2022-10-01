package com.github.otuva.eksisozluk

//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.json.Json
//import java.io.File

//public suspend fun debugUseSessionFromFile() {
//    val file = File("sozluk.session")
//    if (file.exists()) {
//        session = Json.decodeFromString(file.readText())
//
////            if (session.token.expiresAt < Clock.System.now()) {
////                refreshToken()
////                file.writeText(Json.encodeToString(session))
////            }
//
//        buildClient()
//    } else {
//        println("Session file not found.")
//    }
//}

/**
 * temporary main function for testing
 * this will be removed in the future
 * */
//public suspend fun main() {
//    val eksiClient = EksiClient()

//    eksiClient.buildClient()

    // 32132

//    val testing = eksiClient.getEntry(1)
//    val testing = eksiClient.getChannelFilters()
//    val testing = eksiClient.setChannelFilters(eksiClient.createFilters(enableIliskiler = false, enableSpor = false))

//    eksiClient.likeEntry(1)
//    val testing = eksiClient.getTopic(31795, SortingType.Images)
//    val testing1 = eksiClient.getIndexToday()
//    val testing = eksiClient.debugGetResponse(Routes.BaseUrl.path + routes["indexGetFilterChannels"]!!, "POST")

//    println(testing)
//    println(testing1)
//}

private suspend fun main() {
    val eksiClient = EksiClient()

    eksiClient.entry.get(1)
}