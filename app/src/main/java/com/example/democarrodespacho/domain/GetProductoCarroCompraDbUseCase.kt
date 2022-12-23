package com.example.democarrodespacho.domain

import android.util.Log
import com.example.democarrodespacho.data.CarroCompraRepository
import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.data.database.entities.PedidosEntity
import com.example.democarrodespacho.domain.model.Pedidos
import com.example.democarrodespacho.domain.model.Producto
import javax.inject.Inject

class GetProductoCarroCompraDbUseCase @Inject constructor(
    private val repository: CarroCompraRepository
 ){

    //suspend operator fun invoke(nombre: String): List<Producto?> {
    suspend operator fun invoke(nombre: String): Pedidos? {
        val productoEnCarroCompra = repository.getProductoCarroCompraFromDatabase(nombre)
        //Log.d("onCreate", "productos: ${producto.toString()}")
        return productoEnCarroCompra
      //  return null
    }

}