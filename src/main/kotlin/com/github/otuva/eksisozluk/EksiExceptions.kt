package com.github.otuva.eksisozluk

import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.HttpRequest
import io.ktor.serialization.*

fun exceptionHandler(exception: Throwable, request: HttpRequest) {
    // handle not found entries here because api returns 200 for not found entries
    if (exception is JsonConvertException && exception.cause is BadTopicException) {
        val badTopicException = exception.cause as BadTopicException
        throw BadEntryException(badTopicException.message!!)
    }

    val urlString = request.url.toString()
    val responseException = exception as ResponseException
    val statusCode = responseException.response.status.value

    with (urlString) {
        when (statusCode) {
            401 -> throw BadTokenException("Token is invalid or expired")
            500 -> when {
                contains("/v2/user/") -> throw BadUserException("User not found")
                contains("/v2/topic/") -> throw BadTopicException("Topic not found")
            }
            else -> println("Unknown or known (and not implemented) error: ${exception} at ${request.url}")
        }
    }
}

class BadTopicException(message:String): Exception(message)

class BadUserException(message:String): Exception(message)

class BadEntryException(message:String): Exception(message)

class BadUserEntriesException(message:String): Exception(message)

class BadTokenException(message:String): Exception(message)