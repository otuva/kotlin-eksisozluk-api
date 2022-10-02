package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.search.Autocomplete
import com.github.otuva.eksisozluk.responses.AutocompleteResponse
import com.github.otuva.eksisozluk.utils.urlEncode
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

public class SearchApi(private val client: HttpClient, private val userType: UserType) {
    public suspend fun autocomplete(query: String): Autocomplete {
        val url = Routes.baseUrl + Routes.Search.autocomplete.format(urlEncode(query))

        val response = client.get(url)

        val autocompleteResponse: AutocompleteResponse = response.body()

        return autocompleteResponse.data
    }
}