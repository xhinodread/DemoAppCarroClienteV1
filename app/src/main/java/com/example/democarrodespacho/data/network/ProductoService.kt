package com.example.democarrodespacho.data.network

import com.example.democarrodespacho.data.model.ResponseProductoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.util.Log
import okhttp3.RequestBody

class ProductoService @Inject constructor(private val api:ProductoApiClient) {

    suspend fun getResponse(): ResponseProductoModel{
        return withContext(Dispatchers.IO) {
            val response = api.getAllProductos()

            val cuerpo = response.body()
            Log.d("onCreate", "cuerpo: ${cuerpo.toString()}")
            (if(response.isSuccessful){
                cuerpo
            }else{
                null
            })!!

        }
    }

    suspend fun getErrorResponse(): Result<ResponseProductoModel>{
        return withContext(Dispatchers.IO) {
            var movieListResponse: ResponseProductoModel
            val response = api.getAllResultProductos().onSuccess {
                it
            }.onFailure {
                //Log.d("onCreate", "error: ${it.toString()}")
                it.toString()
            }
            response
        }
    }

    suspend fun enviarPostPedido(requestBody: RequestBody){
        val envioPost = api.enviarSolicitudPedidos(requestBody)
        Log.d("onCreate", "ProductoService() - response Post: ${envioPost.toString()}")

    }

}