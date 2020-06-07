package com.picpay.desafio.android.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseUser(
   @PrimaryKey
   val id: Int,
   val img: String,
   val name: String,
   val userName: String
)