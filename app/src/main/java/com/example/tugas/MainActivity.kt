package com.example.tugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.tugas.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomMenu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.homeFragment)
                    true
                }
                R.id.favorit -> {
                    findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                    true
                }
                R.id.list -> {
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                    true
                }
                R.id.profile -> {
                    findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun findNavController(): NavController {
        return findNavController(R.id.fragmentContainerView2)
    }
}
