package com.example.tugas.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.example.tugas.R
import com.example.tugas.databinding.FragmentFavoriteBinding
import com.example.tugas.databinding.FragmentHomeBinding
import com.example.tugas.view.adapter.FavoriteAdapter
import com.example.tugas.view.adapter.ProductsAdapter
import com.example.tugas.viewmodel.FavoriteViewModel
import com.example.tugas.viewmodel.SliderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteAdapter = FavoriteAdapter()
        binding.rvFavorite.adapter = favoriteAdapter

        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        val sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)

        // Panggil fungsi callApiFavorite pada FavoriteViewModel dengan menggunakan userId
        if (userId != null) {
            favoriteViewModel.callApiFavorite(userId)
        }

        // Observasi perubahan data favorite menggunakan LiveData pada FavoriteViewModel
        favoriteViewModel.liveDataFavorite.observe(viewLifecycleOwner, { favoriteList ->
            if (favoriteList != null && favoriteList.isNotEmpty()) {
                // Update data adapter dengan data favorite yang baru
                Log.d("LoginFragment", "User ID: $userId")

                favoriteAdapter.setData(favoriteList)
            } else {
                // Tangani jika data favorite kosong atau gagal dimuat
            }
        })
    }
}
