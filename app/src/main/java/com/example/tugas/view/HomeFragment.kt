package com.example.tugas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.example.tugas.R
import com.example.tugas.databinding.FragmentHomeBinding
import com.example.tugas.view.adapter.NewsUpdateAdapter
import com.example.tugas.view.adapter.ProductsAdapter
import com.example.tugas.viewmodel.NewsViewModel
import com.example.tugas.viewmodel.ProductsViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pref: SharedPreferences
    private val imageListSliders = arrayListOf<SlideModel>()

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)
//        val username = pref.getString("username", "username")
//        binding.tvWelcome.text = "Welcome, $username"

        imageListSliders.add(SlideModel("https://s3images.coroflot.com/user_files/individual_files/large_342665_pEpSJJXkEZDGA_3UtooVFOO88.jpg"))
        imageListSliders.add(SlideModel("https://www.concept-phones.com/wp-content/uploads/2020/05/iPhone-Slide-Pro-concept-design-1-680x450.jpg"))
        imageListSliders.add(SlideModel("https://dimsemenov.com/plugins/royal-slider/img/laptop.png"))
        imageListSliders.add(SlideModel("https://i0.wp.com/www.smartprix.com/bytes/wp-content/uploads/2022/05/iPhone14-1-photoutils.com_.jpeg"))

        val sliderLayout = binding.imageSlider
        sliderLayout.setImageList(imageListSliders)

    }

    override fun onStart() {
        super.onStart()
        handler = Handler()
        runnable = Runnable {
            handler.postDelayed(runnable, 2000)
        }
        handler.post(runnable)

        val viewModelNews = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModelNews.callApiNews()
        viewModelNews.liveDataNews.observe(viewLifecycleOwner, Observer { newsList ->
            if (newsList != null) {
                val newsAdapter = NewsUpdateAdapter(newsList)
                binding.rvNewsUpdate.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvNewsUpdate.adapter = newsAdapter
            }
        })

        val viewModelProduct = ViewModelProvider(this).get(ProductsViewModel::class.java)
        viewModelProduct.callApiProducts()
        viewModelProduct.liveDataProducts.observe(viewLifecycleOwner, Observer { productList ->
            if (productList != null) {
                val productsAdapter = ProductsAdapter(productList)
                binding.rvProducts.layoutManager =
                    GridLayoutManager(requireContext(), 2)
                binding.rvProducts.adapter = productsAdapter
            }
        })
    }
}