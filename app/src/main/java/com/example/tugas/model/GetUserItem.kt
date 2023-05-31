package com.example.tugas.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetUserItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id_users")
    val idUsers: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
): Serializable