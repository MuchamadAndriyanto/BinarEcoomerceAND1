package com.example.tugas.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.example.tugas.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val apiService = ApiClient.create()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var favoriteViewModel: FavoriteViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root

    sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReg.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }

        binding.btnLogin.setOnClickListener {
            val username = binding.emailEditText.text.toString()
            val password = binding.passwordEdiText.text.toString()

            // Panggil metode login
            login(username, password)
        }
      // Declare and initialize favoriteViewModel
        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
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
                        val userId = matchingUser?.idUsers.toString()
                        Log.d("LoginFragment", "User ID: $userId")

                        if (matchingUser != null && matchingUser.password == password) {
                            // Login berhasil
                            Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        // Simpan ID pengguna ke SharedPreferences
                            val sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("userId", matchingUser.idUsers)
                            editor.apply()
                            val buttonClicked = getButtonClicked()

                            // Navigasi ke halaman favorit jika tombol favorit ditekan sebelumnya
                            if (buttonClicked == "favorite") {
                                // Panggil API untuk mendapatkan daftar favorit pengguna
                                favoriteViewModel.callApiFavorite(userId)
                                findNavController().navigate(R.id.action_loginFragment_to_favoriteFragment)
                            }
                            // Navigasi ke halaman profil jika tombol profil ditekan sebelumnya
                            else if (buttonClicked == "profile") {
                                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                            }
                            // Navigasi ke halaman beranda jika tidak ada tombol yang ditekan sebelumnya
                            else {
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            }
                        }
                        } else {
                            // Login gagal
                            Toast.makeText(context, "Username atau password salah", Toast.LENGTH_SHORT).show()
                        }

                    }
            }

            override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
                // Kegagalan koneksi atau request
                Toast.makeText(context, "Terjadi kesalahan saat melakukan login", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun getButtonClicked(): String? {
        val sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        return sharedPreferences.getString("buttonClicked", null)
    }

}