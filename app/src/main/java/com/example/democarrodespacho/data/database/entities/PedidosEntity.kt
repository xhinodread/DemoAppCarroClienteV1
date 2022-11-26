package com.example.democarrodespacho.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.democarrodespacho.domain.model.CarroCompra
import com.example.democarrodespacho.domain.model.Pedidos

@Entity(tableName="carro_compras")
data class PedidosEntity(
    @ColumnInfo(name="id") val id: Int?=0,
    @ColumnInfo(name="cantidad") val cantidad: Int?,
    @ColumnInfo(name="nombre_producto") val producto: String?,
    @ColumnInfo(name="precio") val precio: Int?,
    @ColumnInfo(name="a_pagar") val aPagar: Int?,
    @ColumnInfo(name="foto") val foto: String?,
)
fun Pedidos.toDatabase() = PedidosEntity(id = id, cantidad = cantidad, producto = nombre_producto, precio = precio, aPagar = aPagar, foto = foto )
