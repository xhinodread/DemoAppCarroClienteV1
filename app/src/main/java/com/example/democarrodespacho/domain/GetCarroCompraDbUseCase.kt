package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.CarroCompraRepository
import com.example.democarrodespacho.data.database.entities.toDatabase
import com.example.democarrodespacho.domain.model.CarroCompra
import com.example.democarrodespacho.domain.model.Producto
import javax.inject.Inject

class GetCarroCompraDbUseCase @Inject constructor(
    private val carroCompraRepository: CarroCompraRepository
) {
    suspend operator fun invoke(): List<CarroCompra> {
        val carroCompra = carroCompraRepository.getCarroCompraFromDatabase()
        return if(carroCompra.isNotEmpty()) {
            //Log.d("onCreate", "productos: ${productos.toString()}")
            carroCompra
        } else {
            emptyList()
            // UNA IDEA PUEDE SER, INFORMAR AL USUARIO QUE NO HAY RESPUESTA DEL SERVIDOR
            // Y ENTREGAR EL LISTADO DE ROOMdB
        }
    }
}