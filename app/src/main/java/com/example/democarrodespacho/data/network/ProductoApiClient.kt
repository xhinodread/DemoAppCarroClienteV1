package com.example.democarrodespacho.data.network

import com.example.democarrodespacho.data.model.ResponseProductoModel
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ProductoApiClient {
    @GET("/distribuidora_api/listado_productos.php")
    suspend fun getAllResultProductos(): Result<ResponseProductoModel>

    @GET("/distribuidora_api/listado_productos.php")
    suspend fun getAllProductos(): Response<ResponseProductoModel>

    @POST("/distribuidora_api/recepcion_pedidos.php")
    suspend fun enviarSolicitudPedidos(@Body requestBody: RequestBody): Response<ResponseBody>

}