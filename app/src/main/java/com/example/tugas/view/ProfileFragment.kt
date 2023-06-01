package com.example.tugas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.tugas.R
import com.example.tugas.databinding.FragmentProfileBinding
import com.example.tugas.model.GetUserItem
import com.example.tugas.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    lateinit var listuser: List<GetUserItem>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var model : ProfileViewModel
    private lateinit var oldPassword : String
    private lateinit var id : String
    //lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(this).get(ProfileViewModel::class.java)
        sharedPreferences = requireContext().getSharedPreferences("LOGGED_IN", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("username","username")
//        editor = share.edit()
        id = sharedPreferences.getString("id", "").toString()

        getDataProfile()

    }

    fun getDataProfile() {
        model.getProfileById(id)
        model.dataUserProfile.observe(viewLifecycleOwner){
            if (it != null){
                binding.etUsername.setText(it.name)
                binding.etNama.setText(it.email)
                binding.etAlamat.setText(it.password)
                oldPassword = it.password
            }
        }
    }

    override fun onStart() {
        super.onStart()
        getDataProfile()
    }
}