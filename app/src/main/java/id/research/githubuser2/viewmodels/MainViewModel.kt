package id.research.githubuser2.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.research.githubuser2.models.DetailUserResponse
import id.research.githubuser2.models.ItemsItem
import id.research.githubuser2.models.SearchUserResponse
import id.research.githubuser2.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _mSetUser = MutableLiveData<List<ItemsItem>>()
    val mSetUser: LiveData<List<ItemsItem>> = _mSetUser

    private val _mDetailUser = MutableLiveData<DetailUserResponse>()
    val mDetailUser: LiveData<DetailUserResponse> = _mDetailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
        private const val USER= "login"
    }

    init {
        findUser()
    }

    private fun findUser() {
        _isLoading.value = true
        val mUser = ApiConfig.getApiService().getUser(USER)
        mUser.enqueue(object : Callback<SearchUserResponse>{
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        _mSetUser.value = response.body()?.items as List<ItemsItem>?
                    }
                }
                else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun searchUser(username: String?){
        _isLoading.value = true
        val mUser = ApiConfig.getApiService().getUser(username)
        mUser.enqueue(object : Callback<SearchUserResponse>{
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if(responseBody != null){
                        _mSetUser.value = response.body()?.items as List<ItemsItem>?
                    }
                }
                else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun detailUser(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetail(username)
        client.enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if(response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    if(responseBody != null){
                        _mDetailUser.value = response.body()
                    }
                }
                else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}