package com.example.tugas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tugas.R
import com.example.tugas.databinding.FragmentDetailBinding
import com.example.tugas.databinding.FragmentDetailNewsBinding
import com.example.tugas.model.detail.DetailListProduct
import com.example.tugas.model.detail.DetailNews


class DetailNewsFragment : Fragment() {

    private lateinit var binding: FragmentDetailNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // pengambilan data
        val list = arguments?.getParcelable<DetailNews>("data_news") as DetailNews

        val id = list.id
        val imagepath = list.imagePath
        val title = list.title
        val overview = list.overview
        val date = list.Date

        binding.JudulBerita.text = title
        binding.contentberita.text = overview
        binding.tanggalberita.text = date

        Glide.with(binding.root).load(imagepath).into(binding.ImageBerita)

    }

}