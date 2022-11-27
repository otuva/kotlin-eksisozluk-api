package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.EksiSozluk
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.index.Index
import com.github.otuva.eksisozluk.models.index.IndexToday
import com.github.otuva.eksisozluk.models.index.debe.Debe
import com.github.otuva.eksisozluk.models.index.filter.ChannelName
import com.github.otuva.eksisozluk.models.index.filter.Filter
import com.github.otuva.eksisozluk.models.index.filter.Filters
import com.github.otuva.eksisozluk.models.index.matter.Matters
import com.github.otuva.eksisozluk.models.index.olay.Olay
import com.github.otuva.eksisozluk.responses.GenericResponse
import com.github.otuva.eksisozluk.responses.index.*
import com.github.otuva.eksisozluk.responses.matter.MattersResponse
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
    public suspend fun popular(filters: List<Filter>? = null, page: Int = 1): Index {
        val url = Routes.api + Routes.Index.popular + "?p=$page"

        val response = client.post(url) {
            if (userType == UserType.Anonymous) {
                contentType(ContentType.Application.Json)
                setBody(
                    Filters(filters ?: createFilters())
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
        EksiSozluk.requireUserLogin(userType)

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
        EksiSozluk.requireUserLogin(userType)

        val url = Routes.api + Routes.Index.getChannelFilters

        val response = client.get(url)

        val filtersResponse: FiltersResponse = response.body()

        return filtersResponse.data.filters
    }

    /**
     * Get popular matter index on the main page
     *
     * @param page The page to get
     *
     * @return [Matters] object
     * */
    public suspend fun matters(page: Int = 1): Matters {
        val url = Routes.api + Routes.Index.matters + "?p=$page"

        val response = client.get(url)

        val mattersResponse: MattersResponse = response.body()

        return mattersResponse.data
    }

    /**
     * Get today's matters index on the main page
     *
     * @param page The page to get
     *
     * @return [Matters] object
     * */
    public suspend fun mattersToday(page: Int = 1): Matters {
        val url = Routes.api + Routes.Index.mattersToday + "?p=$page"

        val response = client.get(url)

        val mattersResponse: MattersResponse = response.body()

        return mattersResponse.data
    }

    /**
     * Get entries written today in the past on a given year
     *
     * @param year The year of the entries
     * @page The page to get
     *
     * @return [Index] object
     *
     * @throws [IllegalArgumentException] if the year is not between 1999 and current year
     * */
    public suspend fun todayInPast(year: Int, page: Int = 1): Index {
        require(year in 1999..Year.now().value) { throw IllegalArgumentException("Year must be between 1999 and ${Year.now().value}") }

        val url = Routes.api + Routes.Index.todayInPast.format(year) + "?p=$page"

        val response = client.get(url)

        val indexResponse: IndexResponse = response.body()

        return indexResponse.data
    }

    /**
     * Get entries written by caylak users
     *
     * @param page The page to get
     *
     * @return [Index] object
     * */
    public suspend fun caylak(page: Int = 1): Index {
        val url = Routes.api + Routes.Index.caylak + "?p=$page"

        val response = client.get(url)

        val indexResponse: IndexResponse = response.body()

        return indexResponse.data
    }

    /**
     * Get last entries
     *
     * @param page The page to get
     *
     * @return [Index] object
     * */
    public suspend fun last(page: Int = 1): Index {
        val url = Routes.api + Routes.Index.last + "?p=$page"

        val response = client.get(url)

        val indexResponse: IndexResponse = response.body()

        return indexResponse.data
    }

    /**
     * This function can be used to get the followed topics and matters of the user.
     *
     * @param page The page to get
     *
     * @return [Olay] object
     * */
    @RequiresLogin
    public suspend fun olay(page: Int = 1): Olay {
        EksiSozluk.requireUserLogin(userType)

        val url = Routes.api + Routes.Index.olay + "?p=$page"

        val response = client.get(url)

        val olayResponse: OlayResponse = response.body()

        return olayResponse.data
    }

    public companion object {
        /**
         * Create filters list for index popular function.
         * By default, everything is enabled, and you can disable them by setting their respective parameters to false.
         *
         * For example if you wanted to disable 'iliskiler' listings, you can set [enableIliskiler] to false.
         * */
        public fun createFilters(
            enableSpor: Boolean = true,
            enableSiyaset: Boolean = true,
            enableAnket: Boolean = true,
            enableIliskiler: Boolean = true,
            enableEksiSozluk: Boolean = true,
            enableYetiskin: Boolean = true,
            enableTroll: Boolean = true
        ): List<Filter> {
            return listOf(
                Filter(1, ChannelName.Spor, enableSpor),
                Filter(2, ChannelName.Siyaset, enableSiyaset),
                Filter(4, ChannelName.Anket, enableAnket),
                Filter(5, ChannelName.Iliskiler, enableIliskiler),
                Filter(10, ChannelName.EksiSozluk, enableEksiSozluk),
                Filter(11, ChannelName.Yetiskin, enableYetiskin),
                Filter(39, ChannelName.Troll, enableTroll),
            )
        }
    }
}