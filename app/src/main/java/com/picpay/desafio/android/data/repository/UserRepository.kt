package com.picpay.desafio.android.data.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.picpay.desafio.android.data.api.ApiService
import com.picpay.desafio.android.data.database.UserDatabase
import com.picpay.desafio.android.data.database.entity.asDomainModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class UserRepository (private val database: UserDatabase) {

    val users: LiveData<List<User>> = Transformations.map(database.userDao.getUsers()) {
        it.asDomainModel()
    }

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            val users = ApiService.getService().getUsers().await()
            database.userDao.insertAll(users.asDatabaseModel())
        }
    }
}