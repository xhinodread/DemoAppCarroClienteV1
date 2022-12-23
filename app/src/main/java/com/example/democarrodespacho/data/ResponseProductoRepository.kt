package com.example.democarrodespacho.data

import com.example.democarrodespacho.data.database.dao.ProductoDao
import com.example.democarrodespacho.data.database.entities.ProductoEntity
import com.example.democarrodespacho.data.model.ProductoModel
import com.example.democarrodespacho.data.model.ResponseProductoModel
import com.example.democarrodespacho.data.network.ProductoService
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.domain.model.ResponseProducto
import com.example.democarrodespacho.domain.model.toDomain
import okhttp3.RequestBody
import javax.inject.Inject

class ResponseProductoRepository @Inject constructor(
    private val api: ProductoService,
    private val productoDao: ProductoDao
){
    // REMOTE ***
    suspend fun getAllProductosFromApi(): Result<ResponseProducto> {
        //val response: ResponseProductoModel = api.getResponse()
        val response: Result<ResponseProductoModel> = api.getErrorResponse()
        return response.map { it.toDomain() }
    }

    suspend fun setEnviarPedidoCarroComprasToApi(requestBody: RequestBody): String{
       return api.enviarPostPedido(requestBody)
    }

    // ROOM DATABASE ***
    suspend fun insertAllProductos(productos: List<ProductoEntity>){
        productoDao.addProductos(productos)
    }

    suspend fun getAllProductosFromDatabase():List<Producto>{
        val response: List<ProductoEntity> = productoDao.getProductosDb()
        return response.map { it.toDomain() }
    }

    suspend fun getFilterProductosFromDatabase(nombre: String):List<Producto>{
        val response: List<ProductoEntity> = productoDao.getFilterProductosByNameDb(nombre)
        return response.map { it.toDomain() }
    }

    suspend fun getProductoFromDatabase(nombre: String):Producto{
        val response: ProductoEntity = productoDao.getProductosByNameDb(nombre)
        return response.toDomain()
    }

    suspend fun clearProductos(){
        productoDao.delProductos()
    }
}