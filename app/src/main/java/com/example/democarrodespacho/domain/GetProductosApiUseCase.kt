package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.data.database.entities.toDatabase
import com.example.democarrodespacho.domain.model.Producto
import javax.inject.Inject

class GetProductosApiUseCase @Inject constructor(
    private val repository: ResponseProductoRepository
){
      suspend operator fun invoke(): List<Producto?> {
          val productos = repository.getAllProductosFromApi()
//          Log.d("onCreate", "productos: ${productos.isSuccess}")
//          Log.d("onCreate", "productos: ${productos.toString()}")

          return if(productos.isSuccess) {
              //Log.d("onCreate", "productos: ${productos}")
              val listadoPro: List<Producto>
              val valorLista = productos.map { productosLista->
                  productosLista.data.map { it }
              }
              listadoPro = valorLista.getOrNull()?.map {
                  Producto(id=it.id, nombre = it.nombre, precio = it.precio, foto = it.foto)
              }!!

              repository.clearProductos()
              repository.insertAllProductos(listadoPro.map { it.toDatabase() })

              listadoPro
          } else {
              emptyList()
          }
    }
}