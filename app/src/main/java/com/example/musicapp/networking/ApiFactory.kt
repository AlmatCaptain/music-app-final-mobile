package com.example.musicapp.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val ENDPOINT = "https://www.theaudiodb.com/api/v1/json/1/"

    fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun getClientApi(): ClientApi =
        getRetrofit().create(ClientApi::class.java)

}