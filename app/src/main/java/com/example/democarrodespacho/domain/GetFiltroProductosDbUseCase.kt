package com.example.democarrodespacho.domain

import android.util.Log
import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.domain.model.Producto
import javax.inject.Inject

class GetFiltroProductosDbUseCase @Inject constructor(
    private val repository: ResponseProductoRepository
) {
    suspend operator fun invoke(nombreProducto:String): List<Producto?> {
        val productos = repository.getFilterProductosFromDatabase(nombreProducto)
        //Log.d("onCreate", "filttro productos: ${productos.toString()}")
        if (!productos.isNullOrEmpty()) {
            return productos
        }
        return emptyList()
    }
}