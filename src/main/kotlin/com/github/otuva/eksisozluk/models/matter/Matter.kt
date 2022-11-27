package com.github.otuva.eksisozluk.models.matter

import com.github.otuva.eksisozluk.models.user.entries.TopicId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a matter.
 *
 * @param matterInfo All the information about the matter.
 * @param topicId Information about the topic which the matter belongs to.
 * */
@Serializable
public data class Matter(
    @SerialName("MatterInfo") val matterInfo: MatterInfo,
    @SerialName("TopicId") val topicId: TopicId
)
