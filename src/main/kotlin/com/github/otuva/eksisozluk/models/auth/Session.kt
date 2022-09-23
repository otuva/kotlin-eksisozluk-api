package com.github.otuva.eksisozluk.models.auth

import kotlinx.serialization.Serializable
import java.util.*

/**
 * Represents a EksiSozluk session which includes client variables and token pair.
 * This is just to make authorization things easier
 * [clientSecret] and [clientUniqueId] must be persistent throughout the session.
 * (i.e. Must validate the bearer [token]
 *
 * @param clientSecret random UUID
 * @param clientUniqueId random UUID
 * @param token [EksiToken] instance that can be validated by uuids
 * */
@Serializable
public data class Session(
    @Serializable(with = UUIDSerializer::class) val clientSecret: UUID,
    @Serializable(with = UUIDSerializer::class) val clientUniqueId: UUID,
    val token: EksiToken
)