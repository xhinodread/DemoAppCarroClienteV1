package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.CarroCompraRepository
import javax.inject.Inject

class SetDeletProdItemCarroCompraUseCase @Inject constructor(
    private val carroCompraRepository: CarroCompraRepository
){
    suspend operator fun invoke(elNombreProducto: String) {
        carroCompraRepository.deleteUnProductoEnCarroToDataBase(elNombreProducto)
    }
}