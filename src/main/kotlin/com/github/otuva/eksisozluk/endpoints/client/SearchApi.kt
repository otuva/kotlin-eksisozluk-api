package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.index.Index
import com.github.otuva.eksisozluk.models.search.Autocomplete
import com.github.otuva.eksisozluk.models.search.Search
import com.github.otuva.eksisozluk.models.search.SortOrder
import com.github.otuva.eksisozluk.responses.search.AutocompleteResponse
import com.github.otuva.eksisozluk.responses.index.IndexResponse
import com.github.otuva.eksisozluk.utils.urlEncode
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.datetime.LocalDateTime

public class SearchApi(private val client: HttpClient) {
    public suspend fun autocomplete(query: String): Autocomplete {
        val url = Routes.api + Routes.Search.autocomplete.format(urlEncode(query))

        val response = client.get(url)

        val autocompleteResponse: AutocompleteResponse = response.body()

        return autocompleteResponse.data
    }

    public suspend fun search(
        keywords: String,
        from: LocalDateTime? = null,
        to: LocalDateTime? = null,
        author: String? = null,
        sortOrder: SortOrder = SortOrder.ReverseChronological
    ): Index {
        val url = Routes.api + Routes.Search.search

        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(
                Search(
                    keywords,
                    from,
                    to,
                    author,
                    sortOrder
                )
            )
        }

        val searchResponse: IndexResponse = response.body()

        return searchResponse.data
    }
}