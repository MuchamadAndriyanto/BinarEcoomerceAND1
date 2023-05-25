package com.example.tugas.network

import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.model.GetProductsItem
import com.example.tugas.model.GetSlidersItem
import com.example.tugas.model.GetUserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("news_update")
    fun getAllNews(): Call<List<GetNewsUpdateItem>>

    @GET("users")
    fun getAllUsers(): Call<List<GetUserItem>>

    @GET("sliders/{id}")
    fun getSliderById(
        @Path("id") id : Int,
    ):Call<List<GetSlidersItem>>

    @GET("category_product/1/products")
    fun getAllProduct(): Call<List<GetProductsItem>>

}