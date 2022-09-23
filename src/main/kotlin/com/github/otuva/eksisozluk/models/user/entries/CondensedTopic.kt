package com.github.otuva.eksisozluk.models.user.entries

import com.github.otuva.eksisozluk.models.entry.Entry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent an entry page shown in the users profile page. Note that this is not the same as normal topic
 *
 * @param topicId the id - title pair of the topic
 * @param entry actual entry in the aforementioned topic
 * */
@Serializable
public data class CondensedTopic(
    @SerialName("TopicId") val topicId: TopicId,
    @SerialName("Entry") val entry: Entry
)