package com.example.tugas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.tugas.R
import com.example.tugas.databinding.FragmentHomeBinding
import com.example.tugas.model.imageData
import com.example.tugas.view.adapter.ImageSliderAdapter
import com.example.tugas.view.adapter.NewsUpdateAdapter
import com.example.tugas.viewmodel.NewsViewModel
import com.google.android.play.core.integrity.i

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pref: SharedPreferences
    private lateinit var adap: ImageSliderAdapter
    private val list = ArrayList<imageData>()
    private lateinit var dost: ArrayList<TextView>

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
        for (i in 0 until list.size) {
            dost.add(TextView(requireContext()))
            dost[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            dost[i].textSize = 18f
            binding.dotIndi.addView(dost[i])
        }


    }

    override fun onStart(){
        super.onStart()

        val viewModelNews = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModelNews.callApiNews()
        viewModelNews.livedatamovie.observe(this, Observer{
            if (it != null){
                binding.rvNewsUpdate.layoutManager = GridLayoutManager(context, 2)
                binding.rvNewsUpdate.adapter = NewsUpdateAdapter(it)
            }
        })

    }
}
