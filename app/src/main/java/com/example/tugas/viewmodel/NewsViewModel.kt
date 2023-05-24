package com.example.tugas.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    var livedatamovie: MutableLiveData<List<GetNewsUpdateItem>?> = MutableLiveData()


    fun callApiNews() {
        ApiClient.instance.getAllNews().enqueue(object : Callback<List<GetNewsUpdateItem>> {
            override fun onResponse(
                call: Call<List<GetNewsUpdateItem>>,
                response: Response<List<GetNewsUpdateItem>>
            ) {
                if (response.isSuccessful) {
                    livedatamovie.postValue(response.body())
                } else {
                    livedatamovie.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetNewsUpdateItem>>, t: Throwable) {
                livedatamovie.postValue(null)
            }
        })
    }
}