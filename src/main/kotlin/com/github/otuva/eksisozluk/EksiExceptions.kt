package com.github.otuva.eksisozluk

import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.serialization.*

public fun exceptionHandler(exception: Throwable, request: HttpRequest) {
    // handle not found entries here because api returns 200 for not found entries
    if (exception is JsonConvertException && exception.cause is BadTopicException) {
        val badTopicException = exception.cause as BadTopicException
        throw BadEntryException(badTopicException.message!!)
    }

    val urlString = request.url.toString()
    val responseException = exception as ResponseException
    val statusCode = responseException.response.status.value

    with(urlString) {
        when (statusCode) {
            401 -> throw BadTokenException("Token is invalid or expired")
            500 -> when {
                contains("/v2/user/") -> throw BadUserException("User not found")
                contains("/v2/topic/") -> throw BadTopicException("Topic not found")
            }

            else -> println("Unknown or known (and not implemented) error: $exception at ${request.url}")
        }
    }
}

public class BadTopicException(message: String) : Exception(message)

public class BadUserException(message: String) : Exception(message)

public class BadEntryException(message: String) : Exception(message)

public class NotAuthorizedException(message: String) : Exception(message)

public class BadTokenException(message: String) : Exception(message)

public class TokenExpiredException(message: String) : Exception(message)

public class SessionNotInitializedException(message: String) : Exception(message)