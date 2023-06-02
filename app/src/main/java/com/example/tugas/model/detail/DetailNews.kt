package com.example.tugas.model.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailNews(
    val id: String,
    val imagePath:String,
    val title:String,
    val overview:String,
    val Date:String
) : Parcelable
