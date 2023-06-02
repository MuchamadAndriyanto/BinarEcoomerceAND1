package com.example.tugas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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
                    saveButtonClicked("favorite") // Simpan nilai "favorite"
                    if (isUserLoggedIn()) {
                        findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                    } else {
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                    }
                    true
                }
                R.id.list -> {
                    saveButtonClicked("list") // Simpan nilai "list"
                    findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
                    true
                }
                R.id.profile -> {
                    saveButtonClicked("profile") // Simpan nilai "profile"
                    if (isUserLoggedIn()) {
                        findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                    } else {
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun saveButtonClicked(buttonClicked: String) {
        val sharedPreferences = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("buttonClicked", buttonClicked)
        editor.apply()
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)
        return userId != null
    }

    private fun findNavController(): NavController {
        return supportFragmentManager.findFragmentById(R.id.fragmentContainerView2)!!.findNavController()
    }
}
