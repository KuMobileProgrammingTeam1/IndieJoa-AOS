package com.example.myapp.Retrofit

import com.example.myapp.stage.StageAddressService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://indiejoa-api.fly.dev"

    fun create():RetrofitService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitService::class.java)
    }

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val addressService: StageAddressService by lazy {
        instance.create(StageAddressService::class.java)
    }

}