package com.example.tugas.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugas.R
import com.example.tugas.databinding.ItemProductsBinding
import com.example.tugas.model.GetProductsItem
import com.example.tugas.model.detail.DetailListProduct

class ProductsAdapter(private val listProducts: List<GetProductsItem>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProductsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem: GetProductsItem) {
            binding.tvTitle.text = productItem.name
            binding.tvPrice.text = productItem.price
            Glide.with(binding.root)
                .load(productItem.productImage).into(binding.imgView)

            binding.detailList.setOnClickListener {
                val id = productItem.idProduct
                val imagepath = productItem.productImage
                val title = productItem.name
                val price = productItem.price
                val overview = productItem.description
                val date = productItem.createdAt

                val detail = DetailListProduct(id, imagepath, title, price, overview, date)

                val data = Bundle()
                data.putParcelable("data_listproduct", detail)
                Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_detailFragment, data)
            }
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
