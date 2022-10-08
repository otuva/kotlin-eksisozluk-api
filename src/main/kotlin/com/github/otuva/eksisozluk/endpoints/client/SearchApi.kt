package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.NotAuthorizedException
import com.github.otuva.eksisozluk.annotations.LimitedWithoutLogin
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.index.Index
import com.github.otuva.eksisozluk.models.search.Autocomplete
import com.github.otuva.eksisozluk.models.search.Search
import com.github.otuva.eksisozluk.models.search.SortOrder
import com.github.otuva.eksisozluk.responses.search.AutocompleteResponse
import com.github.otuva.eksisozluk.responses.index.IndexResponse
import com.github.otuva.eksisozluk.utils.urlEncode
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.datetime.LocalDateTime

public class SearchApi(private val client: HttpClient, private val userType: UserType) {

    /**
     * Main function to search for a query.
     *
     * @param query The query to search for
     *
     * @return [Autocomplete] object
     * */
    public suspend fun autocomplete(query: String): Autocomplete {
        val url = Routes.api + Routes.Search.autocomplete.format(urlEncode(query))

        val response = client.get(url)

        val autocompleteResponse: AutocompleteResponse = response.body()

        return autocompleteResponse.data
    }

    /**
     * Detailed search for given parameters.
     * Api will return things including all space separated keywords.
     *
     * @param keywords space separated keywords. Cannot be null but can be empty
     * @param from The starting date of the search. Can be null
     * @param to The ending date of the search. Can be null
     * @param author entries written by this author. Can be null
     * @param sortOrder The order of the results ([SortOrder.ReverseChronological] by default)
     * @param niceOnly Only 'nice' entries. Default is false
     * @param favoritedOnly Only search in user's favorites. This requires login. Default is false
     *
     * @return [Index] object.
     * */
    @LimitedWithoutLogin
    public suspend fun advancedSearch(
        keywords: String,
        from: LocalDateTime? = null,
        to: LocalDateTime? = null,
        author: String? = null,
        sortOrder: SortOrder = SortOrder.ReverseChronological,
        niceOnly: Boolean = false,
        @RequiresLogin favoritedOnly: Boolean = false,
    ): Index {
        check(userType == UserType.Regular || favoritedOnly.not()) { NotAuthorizedException("Only regular users can search in their favorites") }

        val url = Routes.api + Routes.Search.search

        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(
                Search(
                    keywords,
                    from,
                    to,
                    author,
                    sortOrder,
                    niceOnly,
                    favoritedOnly,
                )
            )
        }

        val searchResponse: IndexResponse = response.body()

        return searchResponse.data
    }
}