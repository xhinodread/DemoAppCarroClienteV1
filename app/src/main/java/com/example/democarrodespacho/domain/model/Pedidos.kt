package com.example.democarrodespacho.domain.model

import androidx.room.ColumnInfo
import com.example.democarrodespacho.data.database.entities.PedidosEntity
import com.example.democarrodespacho.data.model.PedidosModel

data class Pedidos(
    val id: Int?,
    val cantidad: Int?,
    val nombre_producto: String?,
    val precio: Int?,
    val aPagar: Int?,
    val foto: String?
)
fun PedidosModel.toDomain() = Pedidos(id, cantidad ,nombre_producto, precio, aPagar, foto)
fun PedidosEntity.toDomain() = Pedidos(id, cantidad, producto, precio, aPagar, foto)