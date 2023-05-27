package com.example.tugas.network

import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.model.GetProductsItem
import com.example.tugas.model.GetSlidersItem
import com.example.tugas.model.GetUserItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("news_update")
    fun getAllNewsUpdate(): Call<List<GetNewsUpdateItem>>

    @GET("users")
    fun getAllUsers(): Call<List<GetUserItem>>

    @GET("sliders")
    fun getSlider():Call<List<GetSlidersItem>>

    @GET("category_product/3/products")
    fun getAllProduct(): Call<List<GetProductsItem>>

    @FormUrlEncoded
    @POST("users")
    fun registerUser(
        @Field("name") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ApiResponse>


}