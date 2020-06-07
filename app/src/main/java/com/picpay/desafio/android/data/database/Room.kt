package com.picpay.desafio.android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.database.dao.UserDao
import com.picpay.desafio.android.data.database.entity.DatabaseUser

private lateinit var INSTANCE: UserDatabase

@Database(entities = [DatabaseUser::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}

fun getDatabase(context: Context): UserDatabase {
    synchronized(UserDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                UserDatabase::class.java,
                "users").build()
        }
    }
    return INSTANCE
}