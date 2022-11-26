package com.example.democarrodespacho.di

import com.example.democarrodespacho.data.network.ProductoApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import android.util.Log
import com.example.democarrodespacho.core.exception.ResultCallAdapterFactory

//private const val URL = "http://10.0.2.2"
private val URL = "http://192.168.1.90/"
//private const val URL = "http://127.0.0.1/"

@InstallIn(SingletonComponent::class)
@Module
object NetworkProductoModule {

    @Singleton
    @Provides
    fun provide2Retrofit(): Retrofit {
        //Log.d("onCreate", "elRe: ${elRe.baseUrl() .toString()}")
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideProductoApiClient(retrofit: Retrofit): ProductoApiClient {
        //Log.d("onCreate", "provideProductoApiClient: ")
        return retrofit.create(ProductoApiClient::class.java)
    }

}
