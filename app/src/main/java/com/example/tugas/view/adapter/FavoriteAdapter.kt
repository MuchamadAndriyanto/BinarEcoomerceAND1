package com.example.tugas.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugas.databinding.ItemFavoriteBinding
import com.example.tugas.model.GetFavouriteItem


class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val listFavorite: MutableList<GetFavouriteItem> = mutableListOf()

    inner class ViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favouriteItem: GetFavouriteItem) {
            binding.titleFav.text = favouriteItem.name
            binding.tvDate.text = favouriteItem.createdAt
            Glide.with(binding.root)
                .load(favouriteItem.productImage)
                .into(binding.imgFav)

            binding.root.setOnClickListener {
                // Handle click event
                // ...
            }
        }
    }

    fun setData(favoriteList: List<GetFavouriteItem>) {
        listFavorite.clear()
        listFavorite.addAll(favoriteList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = listFavorite[position]
        holder.bind(productItem)
    }

    override fun getItemCount(): Int {
        return listFavorite.size
    }
}
