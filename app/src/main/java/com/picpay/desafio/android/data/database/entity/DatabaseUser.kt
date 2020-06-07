package com.picpay.desafio.android.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.model.User

@Entity
data class DatabaseUser(
   @PrimaryKey
   val id: Int,
   val img: String,
   val name: String,
   val username: String
)

/**
 * Map DatabaseUser to domain User
 */
fun List<DatabaseUser>.asDomainModel(): List<User> {
   return map {
      User(
         id = it.id,
         img = it.img,
         name = it.name,
         username = it.username
        )
   }
}