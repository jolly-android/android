package com.example.interviewpractiseapicall.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpractiseapicall.model.User
import com.example.interviewpractiseapicall.service.RetrofitInstance
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users


    fun loadUsers(){
        viewModelScope.launch {
            Log.d("ViewModel",RetrofitInstance.apiInterface.getUsers().toString())
            try{
                val response = RetrofitInstance.apiInterface.getUsers()
                if (response.isSuccessful) {
                    _users.value = response.body() ?: emptyList()
                    //Log.d("API data ", _users.value.toString() )
                } else {
                   Log.d("UsersViewModal", response.errorBody().toString())
                }
            }
            catch (e : Exception){

            }

        }
    }

}