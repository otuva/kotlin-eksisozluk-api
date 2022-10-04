package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.NotAuthorizedException
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.index.Index
import com.github.otuva.eksisozluk.models.index.IndexToday
import com.github.otuva.eksisozluk.models.index.Matters
import com.github.otuva.eksisozluk.models.index.debe.Debe
import com.github.otuva.eksisozluk.models.index.filter.Filter
import com.github.otuva.eksisozluk.models.index.filter.Filters
import com.github.otuva.eksisozluk.responses.*
import com.github.otuva.eksisozluk.responses.index.DebeResponse
import com.github.otuva.eksisozluk.responses.index.FiltersResponse
import com.github.otuva.eksisozluk.responses.index.IndexResponse
import com.github.otuva.eksisozluk.responses.index.IndexTodayResponse
import com.github.otuva.eksisozluk.responses.matter.MattersResponse
import com.github.otuva.eksisozluk.utils.createFilters
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.time.Year

/**
 * Index related endpoints
 * Such as getting the popular index, debe, filters etc.
 *
 * @param client The [HttpClient] instance
 * @param userType The [UserType] instance
 * */
public class IndexApi(private val client: HttpClient, private val userType: UserType) {
    /**
     * Get debe
     *
     * @return [Debe] object
     * */
    public suspend fun debe(): Debe {
        val url = Routes.api + Routes.Index.debe

        val response = client.get(url)

        val debeResponse: DebeResponse = response.body()

        return debeResponse.data
    }

    /**
     * Get today's topics | bugün
     *
     * @param page The page to get
     *
     * @return [IndexToday] object
     * */
    public suspend fun today(page: Int = 1): IndexToday {
        val url = Routes.api + Routes.Index.today + "?p=$page"

        val response = client.get(url)

        val indexResponse: IndexTodayResponse = response.body()

        return indexResponse.data
    }

    /**
     * Get popular topics | gündem
     *
     * You can pass list of [filters] to filter the results. It's recommended to use [createFilters] function.
     *
     * If user is logged in filters are not used because registered user's filters are kept in the server.
     *
     * @param filters The list of filters to use
     * @param page The page to get
     *
     * @return [Index] object
     * */
    public suspend fun popular(filters: List<Filter> = createFilters(), page: Int = 1): Index {
        val url = Routes.api + Routes.Index.popular + "?p=$page"

        val response = client.post(url) {
            if (userType == UserType.Anonymous) {
                contentType(ContentType.Application.Json)
                setBody(
                    Filters(filters)
                )
            }
        }

        val indexResponse: IndexResponse = response.body()

        return indexResponse.data
    }

    /**
     * Note: Instead of using these functions you can [createFilters] and store them locally for the client.
     *
     * Apply channel filters to the index for the current user | gündeminizi kişiselleştirin
     *
     * To set filters you need to supply a list of [filters]. It's recommended to use [createFilters] function.
     *
     * You need to be logged in to use this function.
     *
     * @param filters The list of filters to use
     *
     * @return [GenericResponse] object. If successful, [GenericResponse.success] is true.
     * */
    @RequiresLogin
    public suspend fun setChannelFilters(filters: List<Filter>): GenericResponse {
        check(userType == UserType.Regular) { NotAuthorizedException("Anonymous users cannot do this.") }

        val url = Routes.api + Routes.Index.setChannelFilters

        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(
                Filters(filters)
            )
        }

        return response.body()
    }

    /**
     * Note: Instead of using these functions you can [createFilters] and store them locally for the client.
     *
     * Get current user's channel filters | gündeminizi kişiselleştirin
     *
     * This is not the same as anonymous user's filters. Because getting anonymous user's filters are redundant.
     * You can use the [createFilters] function for anonymous users.
     *
     * You need to be logged in to use this function.
     *
     * @return list of [Filter] objects
     * */
    @RequiresLogin
    public suspend fun getChannelFilters(): List<Filter> {
        check(userType == UserType.Regular) { NotAuthorizedException("Anonymous users cannot do this.") }

        val url = Routes.api + Routes.Index.getChannelFilters

        val response = client.get(url)

        val filtersResponse: FiltersResponse = response.body()

        return filtersResponse.data.filters
    }

    public suspend fun matters(page: Int = 1): Matters {
        val url = Routes.api + Routes.Index.matters + "?p=$page"

        val response = client.get(url)

        val mattersResponse: MattersResponse = response.body()

        return mattersResponse.data
    }

    public suspend fun mattersToday(page: Int = 1): Matters {
        val url = Routes.api + Routes.Index.mattersToday + "?p=$page"

        val response = client.get(url)

        val mattersResponse: MattersResponse = response.body()

        return mattersResponse.data
    }

    public suspend fun todayInPast(year: Int, page: Int = 1): Index {
        check(year in 1999..Year.now().value) { IllegalArgumentException("Year must be between 1999 and ${Year.now().value}") }

        val url = Routes.api + Routes.Index.todayInPast.format(year) + "?p=$page"

        val response = client.get(url)

        val indexResponse: IndexResponse = response.body()

        return indexResponse.data
    }
}