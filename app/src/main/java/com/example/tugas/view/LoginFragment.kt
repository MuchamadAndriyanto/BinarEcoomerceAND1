package com.example.tugas.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tugas.R
import com.example.tugas.databinding.FragmentLoginBinding
import com.example.tugas.network.ApiClient
import com.example.tugas.network.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private val apiService = ApiClient.create()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val username = binding.emailEditText.text.toString()
            val password = binding.passwordEdiText.text.toString()

            // Panggil metode login
            login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        val call = apiService.user(username, password)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.success) {
                        // Login berhasil
                        Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        // Navigasi ke halaman beranda atau halaman lain yang sesuai
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        // Login gagal
                        Toast.makeText(context, "Username atau password salah", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Respons tidak berhasil
                    Toast.makeText(context, "Gagal melakukan login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Kegagalan koneksi atau request
                Toast.makeText(context, "Terjadi kesalahan saat melakukan login", Toast.LENGTH_SHORT).show()
            }

        })
    }
}