package com.github.otuva.eksisozluk.models.matter

import com.github.otuva.eksisozluk.models.user.entries.TopicId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Matter(
    @SerialName("MatterInfo") val matterInfo: MatterInfo,
    @SerialName("TopicId") val topicId: TopicId
)
