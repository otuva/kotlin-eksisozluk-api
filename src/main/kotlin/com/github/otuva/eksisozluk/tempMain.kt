package com.github.otuva.eksisozluk

import com.github.otuva.eksisozluk.models.authentication.Session
//import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

public fun debugUseSessionFromFile(which: String): Session {
    var filename: String

    filename = if (which == "anon") {
        "anon.session"
    } else {
        "sozluk.session"
    }

    val file = File(filename)
    if (file.exists()) {
        val session: Session = Json.decodeFromString(file.readText())
//            if (session.token.expiresAt < Clock.System.now()) {
//                refreshToken()
//                file.writeText(Json.encodeToString(session))
//            }
        return session

    } else {
        throw Exception("Session file not found.")
    }
}

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
//    val eksiSozluk = EksiSozluk(existingSession = debugUseSessionFromFile("anon"))
//
////    val file = File("anon.session")
////    file.writeText(Json.encodeToString(eksiClient.session))
//
//    val testing = eksiSozluk.user.entries("divit")
//
//    println(testing)
    val eksiSozluk = EksiSozluk()

    val entries = eksiSozluk.user.favoriteEntries("ssg")

    println(entries)
}