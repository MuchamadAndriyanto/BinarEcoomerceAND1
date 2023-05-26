package com.example.tugas.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugas.databinding.ItemSlideBinding
import com.example.tugas.model.GetSlidersItem

class ImageSliderAdapter(private val items: List<GetSlidersItem>) : RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSlideBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetSlidersItem) {
            Glide.with(itemView).load(data.image).into(binding.ivSlider)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}