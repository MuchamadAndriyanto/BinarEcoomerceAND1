package com.example.tugas.model

import com.google.gson.annotations.SerializedName

data class DataPostUser(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("password")
    val password: String? = null
)