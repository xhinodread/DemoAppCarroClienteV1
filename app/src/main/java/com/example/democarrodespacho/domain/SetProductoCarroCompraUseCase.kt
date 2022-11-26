package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.CarroCompraRepository
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.domain.model.Producto
import javax.inject.Inject

class SetProductoCarroCompraUseCase @Inject constructor(
    private val carroCompraRepository: CarroCompraRepository
){
    suspend operator fun invoke(elProducto: CarroCompraEntity) {
     carroCompraRepository.setProductoAlCarroToDataBase(elProducto)
    }
}