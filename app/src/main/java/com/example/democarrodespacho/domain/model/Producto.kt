package com.example.democarrodespacho.domain.model

import com.example.democarrodespacho.data.database.entities.ProductoEntity
import com.example.democarrodespacho.data.model.ProductoModel

data class Producto(
    val id: Int,
    val nombre: String?,
    val precio: Int?,
    val foto: String?
)

fun ProductoModel.toDomain() = Producto(id, nombre, precio, foto)
fun ProductoEntity.toDomain() = Producto(id, nombre, precio, foto)
