package com.example.lineup_contests

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitHelper {

    val BASE_URL : String = "https://kontests.net/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
    }

    val apiService : ApiService by lazy {
        retrofitBuilder.build().create(ApiService::class.java)
    }

}