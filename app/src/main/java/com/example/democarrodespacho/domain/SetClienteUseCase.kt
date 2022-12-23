package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.ResponseClienteRepository
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.data.database.entities.ClienteEntity
import com.example.democarrodespacho.domain.model.Cliente
import javax.inject.Inject

class SetClienteUseCase @Inject constructor(
    private val repository: ResponseClienteRepository
) {
    suspend operator fun invoke(elCliente: ClienteEntity) {
        repository.setClienteToDataBase(elCliente)
    }
}