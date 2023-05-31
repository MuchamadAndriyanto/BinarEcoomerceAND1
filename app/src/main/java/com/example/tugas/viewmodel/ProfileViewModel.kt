package com.example.tugas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugas.model.GetUserItem
import com.example.tugas.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val Client: ApiService) : ViewModel() {


    private val livedataUserProfile: MutableLiveData<GetUserItem?> = MutableLiveData()
    val dataUserProfile: LiveData<GetUserItem?> get() = livedataUserProfile

    fun getProfileById(id: String) {
        Client.getProfileUser(id).enqueue(object : Callback<GetUserItem> {
            override fun onResponse(
                call: Call<GetUserItem>,
                response: Response<GetUserItem>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        livedataUserProfile.postValue(responseBody)
                    }
                }
            }
            override fun onFailure(call: Call<GetUserItem>, t: Throwable) {

            }

        })
    }
}