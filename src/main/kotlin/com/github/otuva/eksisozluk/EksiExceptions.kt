package com.github.otuva.eksisozluk

import io.ktor.client.request.HttpRequest

fun exceptionHandler(exception: Throwable, request: HttpRequest) {
    println("Exception cause: ${exception}")
    println("Request url: ${request.url}")

    val urlString = request.url.toString()

    with (urlString) {
        when {
            contains("/v2/user/") -> throw BadUserException("User not found")
            contains("/v2/topic/") -> throw BadUserException("Topic not found")
            else -> println("Unknown or known (and not implemented) error: ${exception} at ${request.url}")
        }
    }
}

class BadTopicException(message:String): Exception(message)

class BadUserException(message:String): Exception(message)

class BadUserEntriesException(message:String): Exception(message)

class BadAuthException(message:String): Exception(message)