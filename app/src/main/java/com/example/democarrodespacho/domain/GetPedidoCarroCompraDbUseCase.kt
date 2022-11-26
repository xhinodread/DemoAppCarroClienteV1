package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.CarroCompraRepository
import com.example.democarrodespacho.data.database.entities.toDatabase
import com.example.democarrodespacho.domain.model.CarroCompra
import com.example.democarrodespacho.domain.model.Pedidos
import com.example.democarrodespacho.domain.model.Producto
import javax.inject.Inject

class GetPedidoCarroCompraDbUseCase @Inject constructor(
    private val carroCompraRepository: CarroCompraRepository
) {
    suspend operator fun invoke(): List<Pedidos> {
        val pedidoCarroCompra = carroCompraRepository.getPedidoCarroCompraFromDatabase()
        return if(pedidoCarroCompra.isNotEmpty()) {
            //Log.d("onCreate", "productos: ${productos.toString()}")
            pedidoCarroCompra
        } else {
            emptyList()
        }
    }
}