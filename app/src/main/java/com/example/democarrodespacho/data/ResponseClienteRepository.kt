package com.example.democarrodespacho.data

import android.util.Log
import com.example.democarrodespacho.data.database.dao.ClienteDao
import com.example.democarrodespacho.data.database.dao.ProductoDao
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.data.database.entities.ClienteEntity
import com.example.democarrodespacho.data.database.entities.ProductoEntity
import com.example.democarrodespacho.domain.model.Cliente
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.domain.model.toDomain
import javax.inject.Inject

class ResponseClienteRepository @Inject constructor(
    private val clienteDao: ProductoDao
) {

    // ROOM DATABASE ***
    /******************/
    suspend fun getClienteFromDatabase():Cliente{
        val response: ClienteEntity = clienteDao.getClienteDb()
        if(response != null) {
            //Log.d("onCreate", response.toString())
            return response.toDomain()
        }
        return Cliente(0,"","","","")
    }

    suspend fun setClienteToDataBase(elCliente: ClienteEntity){
        clienteDao.addCliente(elCliente)
    }

    suspend fun deleteClienteToDataBase(){
        clienteDao.deleteCliente()
    }
}