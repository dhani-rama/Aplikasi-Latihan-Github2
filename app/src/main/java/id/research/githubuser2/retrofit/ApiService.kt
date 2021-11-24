package id.research.githubuser2.retrofit

import id.research.githubuser2.models.DetailUserResponse
import id.research.githubuser2.models.ItemsItem
import id.research.githubuser2.models.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query:String?
    ): Call<SearchUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetail(
        @Query("username") username: String
    ): Call<DetailUserResponse>


}