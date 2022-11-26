package com.example.democarrodespacho.domain

import android.util.Log
import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.domain.model.Producto
import javax.inject.Inject

class GetProductoDbUseCase @Inject constructor(
    private val repository: ResponseProductoRepository){

    //suspend operator fun invoke(nombre: String): List<Producto?> {
    suspend operator fun invoke(nombre: String): Producto {
        val producto = repository.getProductoFromDatabase(nombre)
        //Log.d("onCreate", "productos: ${producto.toString()}")
        return producto
      //  return null
    }

}