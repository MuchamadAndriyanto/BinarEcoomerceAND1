package com.example.tugas.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugas.databinding.ItemNewsupdateBinding
import com.example.tugas.databinding.ItemProductsBinding
import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.model.GetProductsItem

class ProductsAdapter(private val listProducts: List<GetProductsItem>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProductsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem: GetProductsItem) {
            binding.tvTitle.text = productItem.name
            binding.tvPrice.text = productItem.price
            Glide.with(binding.root)
                .load(productItem.productImage).into(binding.imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = listProducts[position]
        holder.bind(productItem)
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }
}