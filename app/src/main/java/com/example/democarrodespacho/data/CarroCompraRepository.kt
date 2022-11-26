package com.example.democarrodespacho.data

import android.util.Log
import com.example.democarrodespacho.data.database.dao.ProductoDao
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.data.database.entities.PedidosEntity
import com.example.democarrodespacho.domain.model.CarroCompra
import com.example.democarrodespacho.domain.model.Pedidos
import com.example.democarrodespacho.domain.model.toDomain
import javax.inject.Inject

//private val carroCompraDao: CarroCompraDao
class CarroCompraRepository @Inject constructor(
    private val productoDao: ProductoDao
){
    // REMOTE ***
    suspend fun getAllProductosFromApi(): Int {
        return 0
    }
    // ROOM DATABASE ***
    /******************/
    suspend fun getTotalCarroCompraFromDatabase():Int?{
        val response: Int = productoDao.getTotalCarrocompraDb()

        Log.d("onCreate", "getTotalCarroCompraFromDatabase: ${response.toString()}")
        //Thread.sleep(700)
        return response
    }

    suspend fun getCarroCompraFromDatabase():List<CarroCompra>{
        val response: List<CarroCompraEntity> = productoDao.getCarroCompraDb()
        return response.map { it.toDomain() }
    }
    suspend fun getProductoCarroCompraFromDatabase(nombreProducto: String): Pedidos {
        val reponse = productoDao.getProductoPedidoCarroCompraDb(nombreProducto)
        return reponse?.toDomain()
    }
    suspend fun getPedidoCarroCompraFromDatabase():List<Pedidos>{
        val response: List<PedidosEntity> = productoDao.getPedidoCarroCompraDb()
        return response.map { it.toDomain() }
    }

    suspend fun setProductoAlCarroToDataBase(elProducto: CarroCompraEntity){
        productoDao.addProductoCarroCompra(elProducto)
    }

    suspend fun deleteUnProductoEnCarroToDataBase(elProductoNombre: String){
        productoDao.deleteUnProductoCarroCompra(elProductoNombre)
    }

    suspend fun deleteTodoProductoEnCarroToDataBase(elProductoNombre: String){
        productoDao.deleteTodoProductoCarroCompra(elProductoNombre)
    }


}