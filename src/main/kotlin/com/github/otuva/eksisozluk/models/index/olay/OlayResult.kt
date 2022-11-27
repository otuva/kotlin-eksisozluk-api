package com.github.otuva.eksisozluk.models.index.olay

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

/**
 * Represents a single followed topic or matter.
 * */
@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("Type")
public sealed class OlayResult