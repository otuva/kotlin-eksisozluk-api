package com.github.otuva.eksisozluk.endpoints.client

import com.github.otuva.eksisozluk.BadUserException
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
     * @return [User] object if user is found else null
     * */
    public suspend fun get(username: String): User? {
        return try {
            val url = Routes.api + Routes.User.user.format(urlEncode(username))

            val response = client.get(url)

            val userResponse: UserResponse = response.body()

            userResponse.data
        } catch (exception: BadUserException) {
            null
        }
    }

    //---------------------------------------
    //-------------entry-related-------------
    //---------------------------------------

    /**
     * Get a user's entries | entry'ler
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no entries on that page or user is not found
     * */
    public suspend fun entries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.entries.format(urlEncode(username)) + "?p=$page"

        return entriesRequest(url)
    }

    /**
     * Get a user's favorite entries | favoriler
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no favorite entries on that page or user is not found
     * */
    public suspend fun favoriteEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.favorites.format(urlEncode(username)) + "?p=$page"

        return entriesRequest(url)
    }

    /**
     * Get a user's top favorite entries | en çok favorilenenler
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no top favorite entries on that page or user is not found
     * */
    public suspend fun mostFavoritedEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.favorited.format(urlEncode(username)) + "?p=$page"

        return entriesRequest(url)
    }

    /**
     * Get a user's last voted entries | son oylananları
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no last voted entries on that page or user is not found
     * */
    public suspend fun lastVotedEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.lastVoted.format(urlEncode(username)) + "?p=$page"

        return entriesRequest(url)
    }

    /**
     * Get most voted entries of last week | bu hafta dikkat çekenleri
     *
     * @param username The username of the user
     * @param page The page to get
     *
     * @return [UserEntries] object. If it's null, it means the user has no most voted entries of last week on that page or user is not found
     * */
    public suspend fun lastWeekMostVotedEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.lastWeekMostVoted.format(urlEncode(username)) + "?p=$page"

        return entriesRequest(url)
    }

    /**
     * Get entries that are written and favorited by the user. | el emeği göz nuru
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [UserEntries] object. If it's null, it means the user has no entries on that page or user is not found
     */
    public suspend fun selfFavoritedEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.selfFavorited.format(urlEncode(username)) + "?p=$page"

        return entriesRequest(url)
    }

    /**
     * Get best entries of the user | en beğenilenleri
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [UserEntries] object. If it's null, it means the user has no entries on that page or user is not found
     * */
    public suspend fun bestEntries(username: String, page: Int = 1): UserEntries? {
        val url = Routes.api + Routes.User.bestEntries.format(urlEncode(username)) + "?p=$page"

        return entriesRequest(url)
    }

    /**
     * Function to handle user entry requests and return [UserEntries] object
     * */
    private suspend fun entriesRequest(url: String): UserEntries? {
        return try {
            val response = client.get(url)

            val userEntriesResponse: UserEntriesResponse = response.body()

            userEntriesResponse.data
        } catch (exception: BadUserException) {
            null
        }
    }

    //------------------
    //------images------
    //------------------

    /**
     * Get a user's images | görseller
     *
     * If user has no images, [UserImages.images] will be null.
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return Null if user does not exist or [UserImages] object.
     * */
    public suspend fun images(username: String, page: Int = 1): UserImages? {
        return try {
            val url = Routes.api + Routes.User.images.format(urlEncode(username)) + "?p=$page"

            val response = client.get(url)

            val userImagesResponse: UserImagesResponse = response.body()

            userImagesResponse.data
        } catch (exception: BadUserException) {
            null
        }
    }

    //---------------------------------------------
    //---------------user-operations---------------
    //---------------------------------------------

    /**
     * Follows the given user. | takip et
     *
     * Note: You need to be logged in to use this method.
     *
     * @param username Username of the user.
     *
     * @return Null if user does not exist or [GenericResponse] object. If [GenericResponse.success] is true, it means the user is followed.
     * */
    @RequiresLogin
    public suspend fun follow(username: String): GenericResponse? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.User.follow

        return usernameRequest(url, username)
    }

    /**
     * Unfollows the given user. | takibi bırak
     *
     * Note: You need to be logged in to use this method.
     *
     * @param username Username of the user.
     *
     * @return Null if user does not exist or [GenericResponse] object. If [GenericResponse.success] is true, it means the user is unfollowed.
     * */
    @RequiresLogin
    public suspend fun unfollow(username: String): GenericResponse? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.User.unfollow

        return usernameRequest(url, username)
    }

    /**
     * Blocks the given user. | engelle
     *
     * Note: You need to be logged in to use this method.
     *
     * @param username Username of the user.
     *
     * @return Null if user does not exist or [GenericResponse] object. If [GenericResponse.success] is true, it means the user is blocked.
     * */
    @RequiresLogin
    public suspend fun block(username: String): GenericResponse? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.User.block

        return usernameRequest(url, username)
    }

    /**
     * Removes the block of the given user. | engellemeyi bırak
     *
     * Note: You need to be logged in to use this method.
     *
     * @param username Username of the user.
     *
     * @return Null if user does not exist or [GenericResponse] object. If [GenericResponse.success] is true, it means the user is unblocked.
     * */
    @RequiresLogin
    public suspend fun unblock(username: String): GenericResponse? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.User.unblock

        return usernameRequest(url, username)
    }

    /**
     * Blocks the topic of the user in index.
     *
     * @param username Username of the user.
     *
     * @return Null if user does not exist or [GenericResponse] object. If [GenericResponse.success] is true, it means the topic is blocked.
     * */
    @RequiresLogin
    public suspend fun blockTopics(username: String): GenericResponse? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.User.blockTopics

        return usernameRequest(url, username)
    }

    /**
     * Removes block for topics of the user in index.
     *
     * @param username Username of the user.
     *
     * @return Null if user does not exist or [GenericResponse] object. If [GenericResponse.success] is true, it means the topic is unblocked.
     * */
    @RequiresLogin
    public suspend fun unblockTopics(username: String): GenericResponse? {
        EksiSozluk.isUserLoggedIn(userType)

        val url = Routes.api + Routes.User.unblockTopics

        return usernameRequest(url, username)
    }

    /**
     * Private method to send username and return response.
     * It's used to handle similar user operations.
     *
     * @param url Url to send request.
     * @param username Username of the user.
     *
     * @return Null if user does not exist or [GenericResponse] object.
     * */
    private suspend fun usernameRequest(url: String, username: String): GenericResponse? {
        return try {
            val response = client.post(url) {
                setBody(
                    FormDataContent(
                        Parameters.build {
                            append("nick", username)
                        }
                    )
                )
            }

            response.body()
        } catch (exception: BadUserException) {
            null
        }
    }

    //---------------------
    //-------matters-------
    //---------------------

    /**
     * Matters that are written by the user. | sorunsallari
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [Matters] object or null if the user has no matters or does not exist.
     * */
    public suspend fun matters(username: String, page: Int = 1): Matters? {
        val url = Routes.api + Routes.User.matters.format(urlEncode(username)) + "?p=$page"

        return matterRequest(url)
    }

    /**
     * Answers written by the user to other matters. | sorunsal yanitlari
     *
     * @param username Username of the user.
     * @param page The page to get.
     *
     * @return [Matters] object or null if user has no answers or does not exist.
     * */
    public suspend fun matterAnswers(username: String, page: Int = 1): Matters? {
        val url = Routes.api + Routes.User.matterAnswers.format(urlEncode(username)) + "?p=$page"

        return matterRequest(url)
    }

    /**
     * Matter request to get matters or matter answers.
     *
     * @param url Url to send request.
     *
     * @return [Matters] object or null if user has no matters or does not exist.
     * */
    private suspend fun matterRequest(url: String): Matters? {
        return try {
            val response = client.get(url)

            val matterAnswersResponse: MattersResponse = response.body()

            matterAnswersResponse.data
        } catch (exception: BadUserException) {
            null
        }
    }
}