package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.NotAuthorizedException
import com.github.otuva.eksisozluk.annotations.LimitedWithoutLogin
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.search.SortOrder
import com.github.otuva.eksisozluk.models.topic.FilterType
import com.github.otuva.eksisozluk.models.topic.Topic
import com.github.otuva.eksisozluk.models.topic.query.Query
import com.github.otuva.eksisozluk.responses.GenericResponse
import com.github.otuva.eksisozluk.responses.topic.FollowResponse
import com.github.otuva.eksisozluk.responses.topic.QueryResponse
import com.github.otuva.eksisozluk.responses.topic.TopicResponse
import com.github.otuva.eksisozluk.utils.urlEncode
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.datetime.LocalDateTime

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
     * To sort or filter entries you can use [filterType] parameter.
     *
     * Note that you can only filter entries if you are logged in. Such as [FilterType.EksiSeyler] or [FilterType.Links].
     *
     * But api doesn't put limitation on sorting with [FilterType.Best] and [FilterType.BestToday]
     *
     * @param topicId The id of the topic
     * @param filterType The sorting type of the topic
     * @param page The page to get
     *
     * @return [Topic] object
     * */
    @LimitedWithoutLogin
    public suspend fun get(topicId: Int, filterType: FilterType = FilterType.All, page: Int = 1): Topic {
        check(
            userType == UserType.Regular || (filterType in listOf(
                FilterType.All,
                FilterType.Best,
                FilterType.BestToday,
                FilterType.Hot
            ))
        ) {
            NotAuthorizedException(
                "Anonymous users cannot do this."
            )
        }

        val url = Routes.api + Routes.Topic.topic.format(topicId) + filterType.value + "?p=$page"

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

        val url = Routes.api + Routes.Topic.search.format(topicId, urlEncode(searchTerm)) + "?p=$page"

        val response = client.get(url)

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!
    }

    public suspend fun advancedSearch(
        topicId: Int,
        keywords: String,
        from: LocalDateTime? = null,
        to: LocalDateTime? = null,
        author: String? = null,
        sortOrder: SortOrder = SortOrder.ReverseChronological,
        page: Int = 1
    ): Topic {
        val searchEncoded = StringBuilder()
        searchEncoded.append("Keywords=${urlEncode(keywords)}")
        searchEncoded.append("&WhenFrom=$from")
        searchEncoded.append("&WhenTo=$to")
        searchEncoded.append("&Author=${urlEncode(author ?: "")}")
        searchEncoded.append("&SortOrder=${sortOrder.value}")

        val url = Routes.api + Routes.Topic.advancedSearch.format(topicId) + '?' + searchEncoded + "&p=$page"

        val response = client.get(url)

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data!!
    }

    public suspend fun query(term: String): Query {
        val url = Routes.api + Routes.Topic.query.format(urlEncode(term))

        val response = client.get(url)

        val queryResponse: QueryResponse = response.body()

        return queryResponse.data
    }

    @RequiresLogin
    public suspend fun follow(topicId: Int): GenericResponse {
        check(userType == UserType.Regular) { NotAuthorizedException("Anonymous users cannot do this.") }

        val url = Routes.api + Routes.Topic.follow

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("Id", topicId.toString())
                    }
                )
            )
        }

        val followResponse: FollowResponse = response.body()

        return followResponse.data
    }

    @RequiresLogin
    public suspend fun unfollow(topicId: Int): GenericResponse {
        check(userType == UserType.Regular) { NotAuthorizedException("Anonymous users cannot do this.") }

        val url = Routes.api + Routes.Topic.unfollow

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("Id", topicId.toString())
                    }
                )
            )
        }

        val followResponse: FollowResponse = response.body()

        return followResponse.data
    }
}