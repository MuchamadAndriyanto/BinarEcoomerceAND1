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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tugas.R
import com.example.tugas.databinding.FragmentLoginBinding
import com.example.tugas.model.GetUserItem
import com.example.tugas.network.ApiClient
import com.example.tugas.network.ApiResponse
import com.example.tugas.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    lateinit var sharepref : SharedPreferences
    private val apiService = ApiClient.create()
    private lateinit var vmuser : UserViewModel

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

        binding.btnReg.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        sharepref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        vmuser = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.emailEditText.text.toString()
            val password = binding.passwordEdiText.text.toString()

            // Panggil metode login
            login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        val call = apiService.getAllUsers()
        call.enqueue(object : Callback<List<GetUserItem>> {
            override fun onResponse(call: Call<List<GetUserItem>>, response: Response<List<GetUserItem>>) {
                if (response.isSuccessful) {
                    val userList = response.body()
                    if (userList != null) {
                        // Cek apakah pengguna dengan username yang diberikan ada dalam daftar pengguna
                        val matchingUser = userList.find { it.email == username }
                        if (matchingUser != null && matchingUser.password == password) {
                            // Login berhasil
                            Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            // Simpan informasi pengguna untuk profil
                            var addData = sharepref.edit()
                            addData.putString("name", username)
                            addData.putString("password", password)
                            addData.apply()
                            // Navigasi ke halaman beranda atau halaman lain yang sesuai
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            // Login gagal
                            Toast.makeText(context, "Username atau password salah", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // Respons tidak berhasil
                    Toast.makeText(context, "Gagal melakukan login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
                // Kegagalan koneksi atau request
                Toast.makeText(context, "Terjadi kesalahan saat melakukan login", Toast.LENGTH_SHORT).show()
            }
        })
    }

}