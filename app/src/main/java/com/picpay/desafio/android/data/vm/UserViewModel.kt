package com.picpay.desafio.android.data.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.database.getDatabase
import com.picpay.desafio.android.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class UserViewModel(application: Application) : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _isNetworkEError = MutableLiveData<Boolean>(false)
    val isNetworkError: LiveData<Boolean>
        get() = _isNetworkEError

    private val usersRepository = UserRepository(getDatabase(application))
    val users = usersRepository.users

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                usersRepository.refreshUsers()
                _isNetworkEError.value = false
            } catch (netWorkError: IOException) {
                if (users.value.isNullOrEmpty())
                    _isNetworkEError.value = true
            }
        }
   }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}