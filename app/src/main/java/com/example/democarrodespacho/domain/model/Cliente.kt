package com.example.democarrodespacho.domain.model

import com.example.democarrodespacho.data.database.entities.ClienteEntity
import com.example.democarrodespacho.data.model.ClienteModel

data class Cliente(
    val id: Int,
    val nombre: String?,
    val rut: String?,
    val email: String?,
    val direccion: String?
)
fun ClienteModel.toDomain() = Cliente(id, nombre, rut, email, direccion)
fun ClienteEntity.toDomain() = Cliente(id, nombre, rut, email, direccion)