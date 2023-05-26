package com.example.tugas.model


import com.google.gson.annotations.SerializedName

data class GetTransHistoryItem(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_trans")
    val idTrans: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("userId")
    val userId: String
)