package com.example.tugas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.model.GetSlidersItem
import com.example.tugas.network.ApiClient
import com.example.tugas.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private var api : ApiService) : ViewModel() {
    val liveDataSliders: MutableLiveData<List<GetSlidersItem>?> = MutableLiveData()

    fun getSlider(){
        api.getSlider().enqueue(object : Callback<List<GetSlidersItem>> {
            override fun onResponse(
                call: Call<List<GetSlidersItem>>,
                response: Response<List<GetSlidersItem>>

            ) {
                if (response.isSuccessful) {
                    liveDataSliders.postValue(response.body())
                } else {
                    liveDataSliders.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<GetSlidersItem>>, t: Throwable) {
                liveDataSliders.postValue(emptyList())
            }
        })
    }
}