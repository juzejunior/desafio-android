package com.picpay.desafio.android.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.database.entity.DatabaseUser
import com.picpay.desafio.android.model.User

@Dao
interface UserDao {
    @Query("select * from databaseuser")
    fun getUsers(): LiveData<List<DatabaseUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( users: List<DatabaseUser>)
}