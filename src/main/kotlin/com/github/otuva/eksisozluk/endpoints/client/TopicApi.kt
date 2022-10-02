package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.NotAuthorizedException
import com.github.otuva.eksisozluk.annotations.LimitedWithoutLogin
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.topic.SortingType
import com.github.otuva.eksisozluk.models.topic.Topic
import com.github.otuva.eksisozluk.responses.TopicResponse
import com.github.otuva.eksisozluk.utils.urlEncode
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * Topic related endpoints
 * Such as getting a topic, filtering, searching a topic etc.
 *
 * @param client The [HttpClient] instance
 * @param userType The [UserType] instance
 * */
public class TopicApi(private val client: HttpClient, private val userType: UserType) {
    /**
     * Get a topic by id | başlık
     *
     * To sort or filter entries you can use [sortingType] parameter.
     *
     * Note that you can only filter entries if you are logged in. Such as [SortingType.EksiSeyler] or [SortingType.Links].
     *
     * But api doesn't put limitation on sorting with [SortingType.Best] and [SortingType.BestToday]
     *
     * @param topicId The id of the topic
     * @param sortingType The sorting type of the topic
     * @param page The page to get
     *
     * @return [Topic] object
     * */
    @LimitedWithoutLogin
    public suspend fun get(topicId: Int, sortingType: SortingType = SortingType.All, page: Int = 1): Topic {
        check(userType == UserType.Regular && !(sortingType == SortingType.Best || sortingType == SortingType.BestToday)) { NotAuthorizedException("Anonymous users cannot do this.") }

        val url = Routes.baseUrl + Routes.Topic.base.format(topicId) + sortingType.value + "?p=$page"

        val response = client.get(url)

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!
    }

    /**
     * Search a term inside a topic | başlık içinde arama
     *
     * To search a user inside topic you can prefix it with '@' character.
     *
     * @param topicId The id of the topic
     * @param searchTerm The term to search
     * @param page The page to get
     *
     * @return [Topic] object. Returned [Topic.entries] can be empty.
     * */
    @RequiresLogin
    public suspend fun searchInTopic(topicId: Int, searchTerm: String, page: Int = 1): Topic {
        check(userType == UserType.Regular) { NotAuthorizedException("Anonymous users cannot do this.") }

        val url = Routes.baseUrl + Routes.Topic.search.format(topicId, urlEncode(searchTerm)) + "?p=$page"

        val response = client.get(url)

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!
    }
}