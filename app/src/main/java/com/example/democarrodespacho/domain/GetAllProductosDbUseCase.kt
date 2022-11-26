package com.example.democarrodespacho.domain

import android.util.Log
import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.domain.model.Producto
import javax.inject.Inject

class GetAllProductosDbUseCase @Inject constructor(
    private val repository: ResponseProductoRepository
){

    suspend operator fun invoke(): List<Producto?> {
        val productos = repository.getAllProductosFromDatabase()
        //Log.d("onCreate", "productos: ${productos.toString()}")
        if (!productos.isNullOrEmpty()) {
            return productos
        }
        return emptyList()
    }

}