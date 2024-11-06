package com.horoscope.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Objects

interface HoroscopeService {
    @GET("api/v1/get-horoscope/daily")
    suspend fun getHoroscope(@Query("sign") sign:String, @Query("day") day:String):HoroscopeResponse
}

object HoroscopeServiceFactory {

    private val BASE_URL = "https://horoscope-app-api.vercel.app/"

    fun getRetrofitService ():HoroscopeService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(HoroscopeService::class.java)
    }
}