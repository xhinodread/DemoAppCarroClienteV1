package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.CarroCompraRepository
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import javax.inject.Inject

class SetDeletTodoProdCarroCompraUseCase @Inject constructor(
    private val carroCompraRepository: CarroCompraRepository
){
    suspend operator fun invoke(elNombreProducto: String) {
        carroCompraRepository.deleteTodoProductoEnCarroToDataBase(elNombreProducto)
    }
}