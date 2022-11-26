package com.example.democarrodespacho.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.democarrodespacho.data.model.ProductoModel
import com.example.democarrodespacho.domain.model.ResponseProducto


//@Entity(tableName = "response_product_table")
data class ResponseProductoEntity(
    @ColumnInfo(name = "msg") val msg: String,
    @ColumnInfo(name = "data") val data: List<ProductoModel>
)

//fun ResponseProducto.toDatabase() = ResponseProductoEntity(msg = msg, data = data)