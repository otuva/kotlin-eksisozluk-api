package com.github.otuva.eksisozluk.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user's karma
 *
 * @param name Karma name represented by [KarmaName]
 * @param value Karma value
 *
 * @see [KarmaName]
 * */
@Serializable
public data class Karma(
    @SerialName("Name") val name: KarmaName,
    @SerialName("Value") val value: Int
)