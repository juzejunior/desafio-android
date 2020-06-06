package com.picpay.desafio.android.data.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.vm.UserViewModel

class UserViewModelFactory(private val repository: UserRepository):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}