package com.example.democarrodespacho.domain.model

import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.data.model.CarroCompraModel

data class CarroCompra(
    val id: Int?,
    val producto: String?,
    val fecha: String?,
)

fun CarroCompraModel.toDomain() = CarroCompra(id, producto, fecha)
fun CarroCompraEntity.toDomain() = CarroCompra(id, producto, fecha)
