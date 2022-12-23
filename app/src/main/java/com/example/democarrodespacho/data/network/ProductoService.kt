package com.example.democarrodespacho.data.network

import com.example.democarrodespacho.data.model.ResponseProductoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.util.Log
import okhttp3.RequestBody
import okhttp3.ResponseBody.Companion.asResponseBody
import org.json.JSONObject
import java.nio.charset.Charset


class ProductoService @Inject constructor(private val api:ProductoApiClient) {

    suspend fun getResponse(): ResponseProductoModel{
        return withContext(Dispatchers.IO) {
            val response = api.getAllProductos()

            val cuerpo = response.body()
            Log.d("onCreate", "getResponse cuerpo: ${cuerpo.toString()}")
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

    suspend fun enviarPostPedido(requestBody: RequestBody): String{
        val envioPost = api.enviarSolicitudPedidos(requestBody)
        //Log.d("onCreate", "ProductoService() - response Post: $envioPost")

        if(envioPost.isSuccessful){
            val cuerpo = envioPost.body()
            val buffer= cuerpo?.source()?.buffer()?.snapshot()?.utf8()
            val jsonObject = JSONObject(buffer)
            val estatus = jsonObject.get("msg")
            //val data = jsonObject.get("data")
            //Log.d("onCreate", "ProductoService() - isSuccessful estatus: $estatus")
            //Log.d("onCreate", "ProductoService() - isSuccessful data: $data")
            return estatus.toString()
        }
        return "fail"
    }

}