package com.example.tugas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tugas.R
import com.example.tugas.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedpref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedpref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnReg.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEdiText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (authenticateUser(email, password)) {
                Toast.makeText(context, "Anda Berhasil Login", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Email atau Password Kamu Ada Yang Salah", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun authenticateUser(email: String, password: String): Boolean {

        return true // Replace with your authentication logic
    }
}