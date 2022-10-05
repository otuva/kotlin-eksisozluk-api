package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.EksiSozluk
import com.github.otuva.eksisozluk.annotations.RequiresLogin
import com.github.otuva.eksisozluk.endpoints.Routes
import com.github.otuva.eksisozluk.models.authentication.UserType
import com.github.otuva.eksisozluk.models.user.User
import com.github.otuva.eksisozluk.models.user.entries.UserEntries
import com.github.otuva.eksisozluk.models.user.images.UserImages
import com.github.otuva.eksisozluk.models.user.matters.Matters
import com.github.otuva.eksisozluk.responses.GenericResponse
import com.github.otuva.eksisozluk.responses.user.UserEntriesResponse
import com.github.otuva.eksisozluk.responses.user.UserImagesResponse
import com.github.otuva.eksisozluk.responses.user.UserResponse
import com.github.otuva.eksisozluk.responses.user.matters.MattersResponse
import com.github.otuva.eksisozluk.utils.urlEncode
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

/**
 * User related endpoints
 * Such as getting user info, entries, images etc.
 *
 * @param client The [HttpClient] instance
 * @param userType The [UserType] instance
 * */
public class UserApi(private val client: HttpClient, private val userType: UserType) {
    /**
     * Get a user by username | biri
     *
     * @param username The username of the user
     *
     * @return [User] object
     * */
    public suspend fun get(username: String): User {
        val url = Routes.api + Routes.User.user.format(urlEncode(username))

        val response = client.get(url)

        val userResponse: UserResponse = response.body()

        return userResponse.data
    }

    /**
     * Get a user's entries | entry'ler
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no entries.
     * */
    public suspend fun entries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.entries.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Get a user's favorite entries | favoriler
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no favorite entries.
     * */
    public suspend fun favoriteEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.favorites.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Get a user's top favorite entries | en çok favorilenenler
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no top favorite entries.
     * */
    public suspend fun mostFavoritedEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.favorited.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Get a user's last voted entries | son oylananları
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no last voted entries.
     * */
    public suspend fun lastVotedEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.lastVoted.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Get most voted entries of last week | bu hafta dikkat çekenleri
     *
     * @param username The username of the user
     * @param page The page to get
     * */
    public suspend fun lastWeekMostVotedEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.lastWeekMostVoted.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Get entries that are written and favorited by the user. | el emeği göz nuru
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [UserEntries] object. If it's null, it means the user has no entries.
     */
    public suspend fun selfFavoritedEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.selfFavorited.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Get best entries of the user | en beğenilenleri
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [UserEntries] object. If it's null, it means the user has no entries.
     * */
    public suspend fun bestEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.bestEntries.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val userEntriesResponse: UserEntriesResponse = response.body()

        return userEntriesResponse.data
    }

    /**
     * Get a user's images | görseller
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [UserImages] object. If user has no images, [UserImages.images] will be null.
     * */
    public suspend fun images(username: String, page: Int = 1): UserImages {
        val url = Routes.api + Routes.User.images.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val userImagesResponse: UserImagesResponse = response.body()

        return userImagesResponse.data
    }

    /**
     * Follows the given user. | takip et
     *
     * Note: You need to be logged in to use this method.
     *
     * @param username Username of the user.
     *
     * @return [GenericResponse] object. If [GenericResponse.success] is true, it means the user is followed.
     * */
    @RequiresLogin
    public suspend fun follow(username: String): GenericResponse {
        EksiSozluk.checkLoginStatus(userType)

        val url = Routes.api + Routes.User.follow

        return sendUsernameAndReturnResponse(url, username)
    }

    /**
     * Unfollows the given user. | takibi bırak
     *
     * Note: You need to be logged in to use this method.
     *
     * @param username Username of the user.
     *
     * @return [GenericResponse] object. If [GenericResponse.success] is true, it means the user is unfollowed.
     * */
    @RequiresLogin
    public suspend fun unfollow(username: String): GenericResponse {
        EksiSozluk.checkLoginStatus(userType)

        val url = Routes.api + Routes.User.unfollow

        return sendUsernameAndReturnResponse(url, username)
    }

    /**
     * Blocks the given user. | engelle
     *
     * Note: You need to be logged in to use this method.
     *
     * @param username Username of the user.
     *
     * @return [GenericResponse] object. If [GenericResponse.success] is true, it means the user is blocked.
     * */
    @RequiresLogin
    public suspend fun block(username: String): GenericResponse {
        EksiSozluk.checkLoginStatus(userType)

        val url = Routes.api + Routes.User.block

        return sendUsernameAndReturnResponse(url, username)
    }

    /**
     * Removes the block of the given user. | engellemeyi bırak
     *
     * Note: You need to be logged in to use this method.
     *
     * @param username Username of the user.
     *
     * @return [GenericResponse] object. If [GenericResponse.success] is true, it means the user is unblocked.
     * */
    @RequiresLogin
    public suspend fun unblock(username: String): GenericResponse {
        EksiSozluk.checkLoginStatus(userType)

        val url = Routes.api + Routes.User.unblock

        return sendUsernameAndReturnResponse(url, username)
    }

    /**
     * Blocks the topic of the user in index.
     *
     * @param username Username of the user.
     *
     * @return [GenericResponse] object. If [GenericResponse.success] is true, it means the topic is blocked.
     * */
    @RequiresLogin
    public suspend fun blockTopics(username: String): GenericResponse {
        EksiSozluk.checkLoginStatus(userType)

        val url = Routes.api + Routes.User.blockTopics

        return sendUsernameAndReturnResponse(url, username)
    }

    /**
     * Removes block for topics of the user in index.
     *
     * @param username Username of the user.
     *
     * @return [GenericResponse] object. If [GenericResponse.success] is true, it means the topic is unblocked.
     * */
    @RequiresLogin
    public suspend fun unblockTopics(username: String): GenericResponse {
        EksiSozluk.checkLoginStatus(userType)

        val url = Routes.api + Routes.User.unblockTopics

        return sendUsernameAndReturnResponse(url, username)
    }

    /**
     * Private method to send username and return response.
     * It's used to handle similar user operations.
     *
     * @param url Url to send request.
     * @param username Username of the user.
     *
     * @return [GenericResponse] object.
     * */
    private suspend fun sendUsernameAndReturnResponse(url: String, username:String): GenericResponse {
        val response = client.post(url) {
            setBody(
                FormDataContent(
                    Parameters.build {
                        append("nick", username)
                    }
                )
            )
        }

        return response.body()
    }

    /**
     * Matters that are written by the user. | sorunsallari
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [Matters] object
     * */
    public suspend fun matters(username: String, page: Int = 1): Matters {
        val url = Routes.api + Routes.User.matters.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val mattersResponse: MattersResponse = response.body()

        return mattersResponse.data
    }

    /**
     * Answers written by the user to other matters. | sorunsal yanitlari
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [Matters] object
     * */
    public suspend fun matterAnswers(username: String, page: Int = 1): Matters {
        val url = Routes.api + Routes.User.matterAnswers.format(urlEncode(username)) + "?p=$page"

        val response = client.get(url)

        val matterAnswersResponse: MattersResponse = response.body()

        return matterAnswersResponse.data
    }
}