package com.example.democarrodespacho.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.democarrodespacho.domain.model.Producto

@Entity(tableName = "productos")
data class ProductoEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int=0,
    @ColumnInfo(name = "nombre") val nombre: String?,
    @ColumnInfo(name = "precio") val precio: Int?,
    @ColumnInfo(name = "foto") val foto: String?
)

fun Producto.toDatabase() = ProductoEntity(nombre = nombre, precio =  precio, foto = foto)