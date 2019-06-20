package com.app.poc

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private var retrofit: Retrofit
    private val BASE_URL = "https://api.stackexchange.com/2.2/"

    private constructor() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {

        private var Instance: RetrofitClient? = null

        @Synchronized
        fun getInstance(): RetrofitClient? {
            if (Instance == null) {
                Instance = RetrofitClient()
            }
            return Instance
        }
    }

    fun getApi():ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

}