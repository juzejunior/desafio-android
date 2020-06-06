package com.picpay.desafio.android.data.api
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

fun <T> callback(response: (response: Response<T>?) -> Unit,
                 failure: (throwable: Throwable?) -> Unit): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            response(response)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
           failure(t)
        }
    }
}