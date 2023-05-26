package com.example.tugas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.tugas.R
import com.example.tugas.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedpref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedpref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val username = binding.usernameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val pass = binding.passwordEditText.text.toString()
        val confirmpass = binding.confirmasiEditText.text.toString()

        val addAkun = sharedpref.edit()
        addAkun.putString("username", username)

        if (username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
            if (pass == confirmpass) {
                addAkun.apply()
                registerUser(username, email, pass)
            } else {
                Toast.makeText(context, "Password Anda Masih Belum Sesuai", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Maaf Masih Ada Data Yang Masih Belum di Isi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_registerFragment_to_loginFragment)
    }
}