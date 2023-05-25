package com.example.tugas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.tugas.databinding.FragmentHomeBinding
import com.example.tugas.model.slider.imageData
import com.example.tugas.view.adapter.ImageSliderAdapter
import com.example.tugas.view.adapter.NewsUpdateAdapter
import com.example.tugas.view.adapter.ProductsAdapter
import com.example.tugas.viewmodel.NewsViewModel
import com.example.tugas.viewmodel.ProductsViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pref: SharedPreferences
    private lateinit var adap: ImageSliderAdapter
    private val list = ArrayList<imageData>()
    private lateinit var dost: ArrayList<TextView>

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

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            var index = 0
            override fun run() {
                if (index == list.size)
                    index = 0
                Log.e("Runnable,", "$index")
                binding.viewpager.setCurrentItem(index)
                index++
                handler.postDelayed(this, 2000)
            }
        }

        list.add(
            imageData(
                "https://s3images.coroflot.com/user_files/individual_files/large_342665_pEpSJJXkEZDGA_3UtooVFOO88.jpg"
            )
        )

        list.add(
            imageData(
                "https://www.concept-phones.com/wp-content/uploads/2020/05/iPhone-Slide-Pro-concept-design-1-680x450.jpg"
            )
        )

        list.add(
            imageData(
                "https://dimsemenov.com/plugins/royal-slider/img/laptop.png"
            )
        )

        list.add(
            imageData(
                "https://i0.wp.com/www.smartprix.com/bytes/wp-content/uploads/2022/05/iPhone14-1-photoutils.com_.jpeg"
            )
        )

        adap = ImageSliderAdapter(list)
        binding.viewpager.adapter = adap
        dost = ArrayList()
        setIndicator()

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDost(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun selectedDost(position: Int) {
        for (i in 0 until list.size) {
            if (i == position)
                dost[i].setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        com.google.android.material.R.color.design_default_color_primary
                    )
                )
            else
                dost[i].setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        com.google.android.material.R.color.design_default_color_secondary
                    )
                )
        }
    }

    private fun setIndicator() {
        binding.dotIndi.removeAllViews()

        for (i in 0 until list.size) {
            val dotView = TextView(requireContext())
            dotView.text = Html.fromHtml("&#9679;", Html.FROM_HTML_MODE_LEGACY).toString()
            dotView.textSize = 18f
            binding.dotIndi.addView(dotView)
            dost.add(dotView)
        }
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }


    override fun onStart() {
        super.onStart()
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
                  GridLayoutManager(context,2)
                binding.rvProducts.adapter = productsAdapter
            }
        })
    }
}
