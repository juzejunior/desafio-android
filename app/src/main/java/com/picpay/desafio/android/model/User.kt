package com.picpay.desafio.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.data.database.entity.DatabaseUser
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable

fun List<User>.asDatabaseModel(): List<DatabaseUser> {
    return map {
        DatabaseUser(
            id = it.id,
            img = it.img,
            name = it.name,
            username = it.username
        )
    }
}