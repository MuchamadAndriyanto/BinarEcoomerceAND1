package com.example.tugas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugas.model.GetFavouriteItem
import com.example.tugas.model.GetSlidersItem
import com.example.tugas.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel@Inject constructor(private var api : ApiService) : ViewModel() {
    val liveDataFavorite: MutableLiveData<List<GetFavouriteItem>?> = MutableLiveData()

    fun callApiFavorite(userId: String){
        api.getAllFavourite(userId).enqueue(object : Callback<List<GetFavouriteItem>> {
            override fun onResponse(
                call: Call<List<GetFavouriteItem>>,
                response: Response<List<GetFavouriteItem>>

            ) {
                if (response.isSuccessful) {
                    liveDataFavorite.postValue(response.body())
                } else {
                    liveDataFavorite.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<GetFavouriteItem>>, t: Throwable) {
                liveDataFavorite.postValue(emptyList())
            }
        })
    }
}