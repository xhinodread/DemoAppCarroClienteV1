package com.example.democarrodespacho.data.model

import com.example.democarrodespacho.data.database.entities.ResponseProductoEntity
import com.example.democarrodespacho.data.model.ProductoModel


data class ResponseProducto(val msg: String, val data: List<ProductoModel>)

fun ResponseProductoModel.toDomain() = ResponseProducto(msg, data)
fun ResponseProductoEntity.toDomain() = ResponseProducto(msg, data)