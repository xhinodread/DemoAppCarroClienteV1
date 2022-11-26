package com.example.democarrodespacho.domain.model

import com.example.democarrodespacho.data.database.entities.ResponseProductoEntity
import com.example.democarrodespacho.data.model.ProductoModel
import com.example.democarrodespacho.data.model.ResponseProductoModel

data class ResponseProducto(val msg: String, val data: List<ProductoModel>)

fun ResponseProductoModel.toDomain() = ResponseProducto(msg, data)
fun ResponseProductoEntity.toDomain() = ResponseProducto(msg, data)