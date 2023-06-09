package com.example.tugas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.model.GetProductsItem
import com.example.tugas.network.ApiClient
import com.example.tugas.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private var api : ApiService): ViewModel() {

    val liveDataProducts: MutableLiveData<List<GetProductsItem>?> = MutableLiveData()

    fun callApiProducts() {
        api.getAllProduct().enqueue(object : Callback<List<GetProductsItem>> {
            override fun onResponse(
                call: Call<List<GetProductsItem>>,
                response: Response<List<GetProductsItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataProducts.postValue(response.body())
                } else {
                    liveDataProducts.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetProductsItem>>, t: Throwable) {
                liveDataProducts.postValue(null)
            }
        })
    }
}