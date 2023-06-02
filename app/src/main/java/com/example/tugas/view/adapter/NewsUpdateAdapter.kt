package com.example.tugas.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugas.R
import com.example.tugas.databinding.ItemNewsupdateBinding
import com.example.tugas.model.GetNewsUpdateItem
import com.example.tugas.model.detail.DetailListProduct
import com.example.tugas.model.detail.DetailNews

class NewsUpdateAdapter(private val listNews: List<GetNewsUpdateItem>) : RecyclerView.Adapter<NewsUpdateAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemNewsupdateBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: GetNewsUpdateItem) {
            binding.tvTitle.text = newsItem.title
            binding.tvDate.text = newsItem.createdAt
            Glide.with(binding.root)
                .load(newsItem.newsImage).into(binding.imgView)

            binding.detailNews.setOnClickListener {
                val id = newsItem.idNews
                val imagepath = newsItem.newsImage
                val title = newsItem.title
                val overview = newsItem.content
                val date = newsItem.createdAt

                val detail = DetailNews(id, imagepath, title, overview, date)

                val data = Bundle()
                data.putParcelable("data_news", detail)
                Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_detailNewsFragment, data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsupdateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = listNews[position]
        holder.bind(newsItem)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}