package com.example.tugas.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugas.databinding.ItemNewsupdateBinding
import com.example.tugas.model.GetNewsUpdateItem

class NewsUpdateAdapter(var listNews : List<GetNewsUpdateItem>) : RecyclerView.Adapter<NewsUpdateAdapter.ViewHolder>(){
    class ViewHolder (var binding : ItemNewsupdateBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsUpdateAdapter.ViewHolder {
        var view = ItemNewsupdateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsUpdateAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = listNews[position].title
        holder.binding.tvDate.text = listNews[position].createdAt
        Glide.with(holder.itemView).load(listNews[position].newsImage).into(holder.binding.imgView)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}