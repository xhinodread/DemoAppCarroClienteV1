package com.example.democarrodespacho.core

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
//object RetrofitHelper {
//
//    //const val URL = "http://192.168.1.92/"
//    const val URL = "" //""http://127.0.0.1/"
//
//    fun getRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//}