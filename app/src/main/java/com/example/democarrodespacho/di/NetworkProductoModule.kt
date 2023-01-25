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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

//private const val URL = "http://10.0.2.2"
//private val URL = "http://192.168.1.90/"
//private const val URL = "http://127.0.0.1/"
private const val URL ="https://chileregion.000webhostapp.com/"

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
            .client(getClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideProductoApiClient(retrofit: Retrofit): ProductoApiClient {
        //Log.d("onCreate", "provideProductoApiClient: ")
        return retrofit.create(ProductoApiClient::class.java)
    }

    @Singleton
    @Provides
    fun getClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HeaderIntercerptor())
        .build()
}

class HeaderIntercerptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", "__________")
            .build()
        return chain.proceed(request)
    }
}
