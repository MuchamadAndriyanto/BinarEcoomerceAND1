package com.example.tugas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugas.model.GetSlidersItem
import com.example.tugas.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private var api : ApiService): ViewModel() {

    val liveDataSlider: MutableLiveData<List<GetSlidersItem>?> = MutableLiveData()

    fun callApiSliders() {
        api.getSlider().enqueue(object : Callback<List<GetSlidersItem>> {
            override fun onResponse(
                call: Call<List<GetSlidersItem>>,
                response: Response<List<GetSlidersItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataSlider.postValue(response.body())
                } else {
                    liveDataSlider.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetSlidersItem>>, t: Throwable) {
                liveDataSlider.postValue(null)
            }
        })
    }
}