package id.research.githubuser2.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.research.githubuser2.models.*
import id.research.githubuser2.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
class MainViewModel : ViewModel() {
    private val _mSetUser = MutableLiveData<List<ItemsItem>>()
    val mSetUser: LiveData<List<ItemsItem>> = _mSetUser

    private val _mDetailUser = MutableLiveData<DetailUserResponse>()
    val mDetailUser: LiveData<DetailUserResponse> = _mDetailUser

    private val _mFollowersUser = MutableLiveData<List<ListFollowersResponseItem>>()
    val mFollowers: LiveData<List<ListFollowersResponseItem>> = _mFollowersUser

    private val _mFollowingUser = MutableLiveData<List<ListFollowingResponseItem>>()
    val mFollowing: LiveData<List<ListFollowingResponseItem>> = _mFollowingUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val USER = "login"
    }

    init {
        findUser()
    }


    private fun findUser() {
        _isLoading.value = true
        val mUser = ApiConfig.getApiService().getUser(USER)
        mUser.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _mSetUser.value = response.body()?.items as List<ItemsItem>?
                    }
                } else {

                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun searchUser(username: String?) {
        _isLoading.value = true
        val mUser = ApiConfig.getApiService().getUser(username)
        mUser.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _mSetUser.value = response.body()?.items as List<ItemsItem>?
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun detailUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetail(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _mDetailUser.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun followersUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<ListFollowersResponseItem>> {
            override fun onResponse(
                call: Call<List<ListFollowersResponseItem>>,
                response: Response<List<ListFollowersResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _mFollowersUser.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ListFollowersResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun followingUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<ListFollowingResponseItem>> {
            override fun onResponse(
                call: Call<List<ListFollowingResponseItem>>,
                response: Response<List<ListFollowingResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _mFollowingUser.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ListFollowingResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}