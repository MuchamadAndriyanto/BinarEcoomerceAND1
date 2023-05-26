package com.example.tugas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.network.ApiClient
import com.example.tugas.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private var api : ApiService): ViewModel() {

    val liveDataNews: MutableLiveData<List<GetNewsUpdateItem>?> = MutableLiveData()

    fun callApiNews() {
        api.getAllNewsUpdate().enqueue(object : Callback<List<GetNewsUpdateItem>> {
            override fun onResponse(
                call: Call<List<GetNewsUpdateItem>>,
                response: Response<List<GetNewsUpdateItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataNews.postValue(response.body())
                } else {
                    liveDataNews.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetNewsUpdateItem>>, t: Throwable) {
                liveDataNews.postValue(null)
            }
        })
    }
}