package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.CarroCompraRepository
import com.example.democarrodespacho.domain.model.Pedidos
import javax.inject.Inject

class GetTotalCarroCompraDbUseCase@Inject constructor(
    private val repository: CarroCompraRepository
){
    suspend operator fun invoke(): Int? = repository.getTotalCarroCompraFromDatabase()
}