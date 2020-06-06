package com.picpay.desafio.android.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://careers.picpay.com/tests/mobdev/";

object ApiService {
    fun getService() : PicPayService {
        val gson: Gson by lazy { GsonBuilder().create() }

        val okHttp: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .build()
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PicPayService::class.java)
    }
}