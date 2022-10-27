package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.BadMatterException
import com.github.otuva.eksisozluk.EksiSozluk
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.matter.Matter
import com.github.otuva.eksisozluk.models.matter.SortingType
import com.github.otuva.eksisozluk.responses.matter.MatterResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

public class MatterApi(private val client: HttpClient, private val userType: UserType) {

    /**
     * Get a single matter by its id.
     *
     * If user clicks a matter from index page, [SortingType.Hot] should be used.
     *
     * @param matterId The id of the matter
     * @param sortingType The sorting type of the matter ([SortingType.All] by default)
     * @param page The page number of the matter. Default is 1
     *
     * @return [Matter] object or null if matter is not found
     * */
    public suspend fun get(matterId: Int, sortingType: SortingType = SortingType.All, page: Int = 1): Matter? {
        val url = Routes.api + Routes.Matter.matter.format(matterId)+ sortingType.value + "?p=$page"

        return matterRequest(url)
    }

    /**
     * Get matter items with snapshot
     *
     * Main usage of this function would be from index.olay()
     *
     * To continue with unread items you can simply use last item from the page and call this function with the latest item id
     * or call this function with the same item id and increase the page number
     *
     * @param matterId The id of the matter
     * @param id The id of the snapshot item
     * @param page The page number of the matter. Default is 1
     *
     * @return [Matter] object or null if matter is not found
     * */
    @RequiresLogin
    public suspend fun getSnapshot(matterId: Int, id: Int, page: Int = 1): Matter? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.Matter.snapshot.format(matterId, id) + "?p=$page"

        return matterRequest(url)
    }

    /**
     * Wrapper function to call matter endpoints.
     * Takes an url and returns a [Matter] object.
     *
     * @param url The url to call
     *
     * @return [Matter] object or null if matter is not found
     * */
    private suspend fun matterRequest(url: String): Matter? {
        return try {
            val response = client.get(url)

            val matterResponse: MatterResponse = response.body()

            matterResponse.data
        } catch (exception: BadMatterException) {
            null
        }
    }
}