package com.github.otuva.eksisozluk.utils

import java.net.URLEncoder

/**
 * To handle usernames with spaces and other special characters
 * */
internal fun urlEncode(string: String): String {
    return URLEncoder.encode(string, "UTF-8").replace("+", "%20")
}

//internal fun urlEncode(string: String, times: Int = 1): String {
//    // encode string given number of times
//    var encodedString = string
//    repeat(times) {
//        encodedString = URLEncoder.encode(encodedString)
//    }
//    return encodedString
//}