package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.BadTopicException
import com.github.otuva.eksisozluk.EksiSozluk
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
import com.github.otuva.eksisozluk.utils.toInt
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

    //------------------------------------------
    //--------------topic-&-search--------------
    //------------------------------------------

    /**
     * Get a topic by id | başlık
     *
     * To sort or filter entries you can use [filterType] parameter.
     *
     * Filters you **can** use without logging-in are:
     * - [FilterType.All]
     * - [FilterType.Best]
     * - [FilterType.BestToday]
     * - [FilterType.Hot]
     *
     * Filters you **cannot** use without logging-in are:
     * - [FilterType.Following]
     * - [FilterType.Links]
     * - [FilterType.Images]
     * - [FilterType.EksiSeyler]
     *
     * @param topicId The id of the topic
     * @param filterType The sorting type of the topic
     * @param page The page to get
     *
     * @return Null if the topic is not found [Topic] object otherwise
     * */
    @LimitedWithoutLogin
    public suspend fun get(topicId: Int, filterType: FilterType = FilterType.All, page: Int = 1): Topic? {
        require(
            userType == UserType.Regular || (filterType in listOf(
                FilterType.All,
                FilterType.Best,
                FilterType.BestToday,
                FilterType.Hot
            ))
        ) {
            throw NotAuthorizedException(
                "Anonymous users cannot do this."
            )
        }

        val url = Routes.api + Routes.Topic.topic.format(topicId) + filterType.value + "?p=$page"

        return topicRequest(url)
    }

    /**
     * Search a term inside a topic | başlık içinde arama
     *
     * To search a user inside topic you can prefix it with '@' character.
     *
     * Returned [Topic.entries] can be empty.
     *
     * @param topicId The id of the topic
     * @param searchTerm The term to search
     * @param page The page to get
     *
     * @return Null if the topic is not found [Topic] object otherwise.
     * */
    @RequiresLogin
    public suspend fun searchInTopic(topicId: Int, searchTerm: String, page: Int = 1): Topic? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.Topic.search.format(topicId, urlEncode(searchTerm)) + "?p=$page"

        return topicRequest(url)
    }

    /**
     * Function to use when you want to filter the results according to the [SearchApi.advancedSearch] results.
     * Or when you want to search specific topics.
     *
     * @param topicId The id of the topic ([com.github.otuva.eksisozluk.models.index.topic.IndexedTopic.topicId])
     * @param keywords space separated keywords. Cannot be null but can be empty
     * @param from The starting date of the search. Can be null
     * @param to The ending date of the search. Can be null
     * @param author entries written by this author. Can be null
     * @param sortOrder The order of the results ([SortOrder.ReverseChronological] by default)
     * @param niceOnly Only 'nice' entries. Default is false
     * @param favoritedOnly Only search in user's favorites. This requires login. Default is false
     * @param page The page to get (1 by default)
     *
     * @return Null if the topic is not found [Topic] object otherwise.
     * */
    public suspend fun advancedSearch(
        topicId: Int,
        keywords: String,
        from: LocalDateTime? = null,
        to: LocalDateTime? = null,
        author: String? = null,
        sortOrder: SortOrder = SortOrder.ReverseChronological,
        niceOnly: Boolean = false,
        @RequiresLogin favoritedOnly: Boolean = false,
        page: Int = 1
    ): Topic? {
        require(userType == UserType.Regular || favoritedOnly.not()) { throw NotAuthorizedException("Only regular users can search in their favorites") }

        val searchEncoded = StringBuilder()
        searchEncoded.append("Keywords=${urlEncode(keywords)}")
        searchEncoded.append("&WhenFrom=$from")
        searchEncoded.append("&WhenTo=$to")
        searchEncoded.append("&Author=${urlEncode(author ?: "")}")
        searchEncoded.append("&SortOrder=${sortOrder.value}")
        searchEncoded.append("&NiceOnly=${niceOnly.toInt()}")
        searchEncoded.append("&FavoritedOnly=${favoritedOnly.toInt()}")

        val url = Routes.api + Routes.Topic.advancedSearch.format(topicId) + '?' + searchEncoded + "&p=$page"

        return topicRequest(url)
    }

    /**
     * Focus specific entries in a topic for followed topics.
     *
     * Main usage would be from index.olay() snapshot entries
     *
     * To continue with unread entries you can simply use last entry from the page and call this function with the latest entry id
     * or call this function with the same entry id and increase the page number
     *
     * @param topicId The id of the topic
     * @param entryId The id of the entry to focus
     * @param page The page to get
     *
     * @return Null if the topic is not found [Topic] object otherwise.
     * */
    @RequiresLogin
    public suspend fun getSnapshot(topicId: Int, entryId: Int, page: Int = 1): Topic? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.Topic.snapshot.format(topicId, entryId) + "?p=$page"

        return topicRequest(url)
    }

    /**
     * Returns the topic response for given url
     *
     * @param url The url to get the topic
     *
     * @return Null if the topic is not found [Topic] object otherwise.
     * */
    private suspend fun topicRequest(url: String): Topic? {
        return try {
            val response = client.get(url)

            val topicResponse: TopicResponse = response.body()

            topicResponse.data
        } catch (exception: BadTopicException) {
            null
        }
    }

    //------------------------------------------
    //--------------query-topic-id--------------
    //------------------------------------------

    /**
     * To search a topic by a string. If you want to search disambiguation topics, call the function [com.github.otuva.eksisozluk.models.topic.Disambiguation.toQueryString]'.
     *
     * @param term The string to search
     *
     * @return [Query] object. Topic id would be Query.queryData.topicId
     * */
    public suspend fun query(term: String): Query {
        val url = Routes.api + Routes.Topic.query.format(urlEncode(term))

        val response = client.get(url)

        val queryResponse: QueryResponse = response.body()

        return queryResponse.data
    }

    //------------------------------------------------
    //----------------topic-operations----------------
    //------------------------------------------------

    /**
     * Follows a topic. This requires login.
     *
     * Note that api won't validate topic id. So if you pass an invalid topic id, it will return [GenericResponse.success] as true.
     *
     * @param topicId The id of the topic
     *
     * @return [GenericResponse] object
     * */
    @RequiresLogin
    public suspend fun follow(topicId: Int): GenericResponse {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.Topic.follow

        return followRequest(url, topicId)
    }

    /**
     * Unfollows a topic. This requires login.
     *
     * Note that api won't validate topic id. So if you pass an invalid topic id, it will return [GenericResponse.success] as true.
     *
     * @param topicId The id of the topic
     *
     * @return [GenericResponse] object
     * */
    @RequiresLogin
    public suspend fun unfollow(topicId: Int): GenericResponse {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.Topic.unfollow

        return followRequest(url, topicId)
    }

    /**
     * Private function to handle follow and unfollow requests
     *
     * Note that api won't validate topic id. So if you pass an invalid topic id, it will return [GenericResponse.success] as true.
     *
     * @param url The url to send the request
     * @param topicId The id of the topic
     *
     * @return [GenericResponse] object
     * */
    private suspend fun followRequest(url: String, topicId: Int): GenericResponse {
        // because api doesn't validate if the topic is valid or not
        // we don't need to try catch it
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