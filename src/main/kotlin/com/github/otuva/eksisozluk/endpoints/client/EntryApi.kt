package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.EksiSozluk
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.entry.Entry
import com.github.otuva.eksisozluk.models.entry.favorite.CaylakFavorites
import com.github.otuva.eksisozluk.models.entry.favorite.FavoriteData
import com.github.otuva.eksisozluk.models.entry.favorite.Favorites
import com.github.otuva.eksisozluk.models.topic.Topic
import com.github.otuva.eksisozluk.responses.GenericResponse
import com.github.otuva.eksisozluk.responses.entry.favorite.CaylakFavoritesResponse
import com.github.otuva.eksisozluk.responses.entry.favorite.FavoriteDataResponse
import com.github.otuva.eksisozluk.responses.entry.favorite.FavoritesResponse
import com.github.otuva.eksisozluk.responses.topic.TopicResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

/**
 * Entry related endpoints
 *
 * @param client The [HttpClient] instance
 * @param userType The [UserType] instance
 * */
public class EntryApi(private val client: HttpClient, private val userType: UserType) {

    /**
     * Get a single entry by id. Note that this function only returns the entry content and not title. | tek entry
     * To get the content with title use [getAsTopic]
     *
     * @param entryId The id of the entry
     *
     * @return null if entry is not found else [Entry] object
     * */
    public suspend fun get(entryId: Int): Entry? {
        val url = Routes.api + Routes.Entry.entry.format(entryId)

        val response = client.get(url)

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data?.getFirstEntry()
    }

    /**
     * Get entry content with title. | başlıklı tek entry
     *
     * @param entryId The id of the entry
     *
     * @return null if entry is not found else [Topic] object
     * */
    public suspend fun getAsTopic(entryId: Int): Topic? {
        val url = Routes.api + Routes.Entry.entry.format(entryId)

        val response = client.get(url)

        val topicResponse: TopicResponse = response.body()

        return topicResponse.data
    }

    /**
     * Like an entry | şükela
     *
     * @param entryId The id of the entry
     *
     * @return [GenericResponse] object. It's kinda useless.
     * */
    public suspend fun like(entryId: Int): GenericResponse {
        val url = Routes.api + Routes.Entry.vote

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("Rate", "1")
                        append("Id", entryId.toString())
                    }
                )
            )
        }

        return response.body()
    }

    /**
     * Dislikes given entry. | çok kötü
     *
     * @param entryId The id of the entry
     *
     * @return [GenericResponse] object. It's kinda useless.
     * */
    public suspend fun dislike(entryId: Int): GenericResponse {
        val url = Routes.api + Routes.Entry.vote

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("Rate", "-1")
                        append("Id", entryId.toString())
                    }
                )
            )
        }

        return response.body()
    }

    /**
     * Add entry to favorites.
     * You need to be logged in to do this.
     *
     * @param entryId The id of the entry
     *
     * @return [FavoriteData] object
     * */
    @RequiresLogin
    public suspend fun favorite(entryId: Int): FavoriteData {
        EksiSozluk.requireUserLogin(userType)

        val url = Routes.api + Routes.Entry.favorite

        return favoriteRequest(url, entryId)
    }

    /**
     * Remove entry from favorites.
     * You need to be logged in to do this.
     *
     * @param entryId The id of the entry
     *
     * @return [FavoriteData] object
     * */
    @RequiresLogin
    public suspend fun unfavorite(entryId: Int): FavoriteData {
        EksiSozluk.requireUserLogin(userType)

        val url = Routes.api + Routes.Entry.unfavorite

        return favoriteRequest(url, entryId)
    }

    /**
     * Get list of users who favorited given entry.
     *
     * @param entryId The id of the entry
     *
     * @return [Favorites] object
     * */
    @RequiresLogin
    public suspend fun favorites(entryId: Int): Favorites {
        EksiSozluk.requireUserLogin(userType)

        val url = Routes.api + Routes.Entry.favorites.format(entryId)

        val response = client.get(url)

        val topicResponse: FavoritesResponse = response.body()

        return topicResponse.data
    }

    /**
     * Get lists of caylaks who favorited given entry.
     *
     * @param entryId The id of the entry
     *
     * @return [CaylakFavorites] object
     * */
    @RequiresLogin
    public suspend fun caylakFavorites(entryId: Int): CaylakFavorites {
        EksiSozluk.requireUserLogin(userType)

        val url = Routes.api + Routes.Entry.caylakFavorites.format(entryId)

        val response = client.get(url)

        val topicResponse: CaylakFavoritesResponse = response.body()

        return topicResponse.data
    }

    /**
     * Private function to handle favorite and unfavorite requests.
     *
     * @param url The url to send request to
     * @param entryId The id of the entry
     *
     * @return [FavoriteData] object
     * */
    private suspend fun favoriteRequest(url: String, entryId: Int): FavoriteData {

        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("Id", entryId.toString())
                    }
                )
            )
        }

        val favoriteDataResponse: FavoriteDataResponse = response.body()

        return favoriteDataResponse.data
    }
}