package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.ResponseClienteRepository
import com.example.democarrodespacho.domain.model.Cliente
import javax.inject.Inject

class GetClienteDbUseCase @Inject constructor(
    private val repository: ResponseClienteRepository
) {
    suspend operator fun invoke(): Cliente {
        val cliente = repository.getClienteFromDatabase()
        //Log.d("onCreate", "productos: ${productos.toString()}")
        return cliente
    }
}