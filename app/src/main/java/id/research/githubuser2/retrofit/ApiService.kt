package id.research.githubuser2.retrofit

import id.research.githubuser2.models.DetailUserResponse
import id.research.githubuser2.models.ListFollowersResponseItem
import id.research.githubuser2.models.ListFollowingResponseItem
import id.research.githubuser2.models.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") query: String?
    ): Call<SearchUserResponse>

    @GET("users/{username}")
    fun getDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ListFollowersResponseItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ListFollowingResponseItem>>


}