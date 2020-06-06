package com.picpay.desafio.android.data.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.model.User

class UserViewModel(private val repository: UserRepository) : ViewModel() {
   private val _users = MutableLiveData<List<User>>()
   val users: LiveData<List<User>>
       get() = _users

    fun getUsers() {
      repository.getUsers(
          {
            _users.value = it
          }, {

          }
      )
   }
}