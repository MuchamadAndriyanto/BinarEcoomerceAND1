package com.example.tugas.model


import com.google.gson.annotations.SerializedName

data class GetSlidersItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String
)