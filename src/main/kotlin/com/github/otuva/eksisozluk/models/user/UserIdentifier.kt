package com.github.otuva.eksisozluk.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent a nick-id pair of a user
 *
 * @param nick User's nick
 * @param id User's internal id
 * */
@Serializable
public data class UserIdentifier(
    @SerialName("Nick") val nick: String,
    @SerialName("Id") val id: Int
)