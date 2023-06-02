package com.example.tugas.network

import com.example.tugas.model.GetCartItem
import com.example.tugas.model.GetFavouriteItem
import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.model.GetProductsItem
import com.example.tugas.model.GetSlidersItem
import com.example.tugas.model.GetTransHistoryItem
import com.example.tugas.model.GetUserItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    @GET("users/{id}?")
    fun getProfileUser(@Path("id") id:String): Call<GetUserItem>

    @GET("users/{id}")
    fun getAllUser():Call<List<GetUserItem>>

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

    @FormUrlEncoded
    @POST("users")
    fun user(
        @Field("email") username: String,
        @Field("password") password: String
    ): Call<ApiResponse>

    //Transaction History
    @GET("users/{id}/transhistory")
    fun getUserTransactionHistory(@Path("id") id: Int): Call<List<GetTransHistoryItem>>

    @POST("users/{id}/transhistory")
    fun postTransactionHistory(@Path("id") id: Int): Call<List<GetTransHistoryItem>>
    @DELETE("users/{id}/transhistory/{id_trans}")
    fun deleteTransactionHistory(
        @Path("id") id: Int,
        @Path("id_trans") id_trans: Int
    ): Call<List<GetTransHistoryItem>>

    //Cart
    @GET("users/{id}/cart")
    fun getUserCart(@Path("id") id: String): Call<List<GetCartItem>>

    @FormUrlEncoded
    @POST("users/{id}/cart")
    fun addCart(
        @Path("id") id:String,
        @Field("name") name:String,
        @Field("product_image") productImage:String,
        @Field("price") price:Int,
        @Field("description") description:String,
    ):Call<List<GetCartItem>>

    @DELETE("users/{id}/cart/{id_cart}")
    fun deleteCart(
        @Path("id") id: Int,
        @Path("id_cart") id_cart: Int
    ): Call<List<GetCartItem>>

    //Favourite
    @GET("users/{id}/favourite")
    fun getFavourite(@Path("id") id: String): Call<List<GetFavouriteItem>>

    @POST("users/{id}/favourite")
    fun postFavourite(@Path("id") id: Int):Call<List<GetFavouriteItem>>

    @FormUrlEncoded
    @POST("users/{id}/favourite")
    fun addFavouriteProduct(
        @Path("id") id:String,
        @Field("name") name:String,
        @Field("product_image") productImage:String,
        @Field("price") price:Int,
        @Field("description") description:String,
    ):Call<GetFavouriteItem>

    @DELETE("users/{userId}/favourite/{id}")
    fun deleteFavouriteProduct(
        @Path("userId") userId:String,
        @Path("id") id:String
    ):Call<GetFavouriteItem>

    @DELETE("users/{id}/cart/{id_fav}")
    fun deleteFavourite(
        @Path("id") id: Int,
        @Path("id_fav") id_fav: Int
    ): Call<List<GetFavouriteItem>>

}