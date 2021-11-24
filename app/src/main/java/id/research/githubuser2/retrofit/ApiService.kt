package id.research.githubuser2.retrofit

import id.research.githubuser2.models.DetailUserResponse
import id.research.githubuser2.models.ListFollowersResponseItem
import id.research.githubuser2.models.ListFollowingResponseItem
import id.research.githubuser2.models.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query: String?
    ): Call<SearchUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ListFollowersResponseItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ListFollowingResponseItem>>


}