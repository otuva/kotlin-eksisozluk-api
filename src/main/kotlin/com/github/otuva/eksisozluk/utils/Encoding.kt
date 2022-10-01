package com.github.otuva.eksisozluk.utils

/**
 * To handle usernames with spaces
 * */
internal fun encodeSpaces(string: String): String {
    return string.replace(" ", "%20")
}