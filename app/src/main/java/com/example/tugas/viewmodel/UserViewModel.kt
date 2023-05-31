package com.example.tugas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tugas.model.DataPostUser
import com.example.tugas.model.GetUserItem
import com.example.tugas.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel@Inject constructor(private var api : ApiService) : ViewModel() {

    private var livedatauser : MutableLiveData<List<DataPostUser>> = MutableLiveData()

    private var livedatauserlogin : MutableLiveData<List<GetUserItem>> = MutableLiveData()

    fun getlivedatauser(): MutableLiveData<List<DataPostUser>>{
        return livedatauser
    }

    fun getlivedatauserlogin() : MutableLiveData<List<GetUserItem>>{
        return livedatauserlogin
    }

/*    fun getregister(User : UsersItem){
        api.postUser(User).enqueue(object : Callback<List<DataPostUser>> {
            override fun onResponse(
                call: Call<List<DataPostUser>>,
                response: Response<List<DataPostUser>>
            ) {
                if (response.isSuccessful){
                    livedatauser.postValue(response.body())
                }else{
                    livedatauser.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<DataPostUser>>, t: Throwable) {
                livedatauser.postValue(emptyList())
            }

        })

    }*/

    fun getlogin(){
        api.getAllUser().enqueue(object : Callback<List<GetUserItem>>{
            override fun onResponse(
                call: Call<List<GetUserItem>>,
                response: Response<List<GetUserItem>>
            ) {
                if (response.isSuccessful){
                    livedatauserlogin.postValue(response.body())
                }else{
                    livedatauserlogin.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
                livedatauserlogin.postValue(emptyList())
            }

        })
    }
}
