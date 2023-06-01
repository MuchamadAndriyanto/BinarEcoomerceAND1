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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.example.tugas.R
import com.example.tugas.databinding.FragmentHomeBinding
import com.example.tugas.view.adapter.ImageSliderAdapter
import com.example.tugas.view.adapter.NewsUpdateAdapter
import com.example.tugas.view.adapter.ProductsAdapter
import com.example.tugas.viewmodel.HomeViewModel
import com.example.tugas.viewmodel.NewsViewModel
import com.example.tugas.viewmodel.ProductsViewModel
import com.example.tugas.viewmodel.SliderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        super.onViewCreated(view, savedInstanceState)
        val sliderViewModel = ViewModelProvider(this).get(SliderViewModel::class.java)
        val sharedPreferences =
            requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)

        // Panggil fungsi callApiSliders pada SliderViewModel
        sliderViewModel.callApiSliders()

        // Observasi perubahan data slider menggunakan LiveData pada SliderViewModel
        sliderViewModel.liveDataSlider.observe(viewLifecycleOwner, { sliders ->
            if (sliders != null && sliders.isNotEmpty()) {
                // Gunakan data sliders untuk mengisi imageListSliders
                for (slider in sliders) {
                    imageListSliders.add(SlideModel(slider.image))
                }
                val slidersLayout = binding.imageSliders
                slidersLayout.setImageList(imageListSliders)
            } else {
                // Tangani jika data slider kosong atau gagal dimuat
            }
        })
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
        viewModelNews.liveDataNews.observe(viewLifecycleOwner, Observer { newsupdateList ->
            if (newsupdateList != null) {
                val newsAdapter = NewsUpdateAdapter(newsupdateList)
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