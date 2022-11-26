package com.example.democarrodespacho.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.democarrodespacho.domain.model.CarroCompra

@Entity(tableName="carro_compras")
data class CarroCompraEntity(
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id") val id: Int?=0,
    @ColumnInfo(name="nombre_producto") val producto: String?,
    @ColumnInfo(name = "fecha_pedido") val fecha: String?,
)
fun CarroCompra.toDatabase() = CarroCompraEntity(producto = producto, fecha = fecha)