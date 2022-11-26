package com.example.democarrodespacho.data

import com.example.democarrodespacho.data.database.dao.ProductoDao
import com.example.democarrodespacho.data.database.entities.ProductoEntity
import com.example.democarrodespacho.data.model.ResponseProductoModel
import com.example.democarrodespacho.data.network.ProductoService
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.domain.model.toDomain
import javax.inject.Inject

class ProductoRepository @Inject constructor(
    private val api: ProductoService,
    private val productoDao: ProductoDao
){

    // REMOTE ***
    suspend fun getAllProductosFromApi(): List<Producto> {
        val response: ResponseProductoModel = api.getResponse()
        //return response
        return response.data.map { it.toDomain() }
    }

    // ROOM DATABASE ***
    suspend fun insertAllProductos(productos: List<ProductoEntity>){
        productoDao.addProductos(productos)
    }

}