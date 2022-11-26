package com.example.democarrodespacho.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity

@Dao
interface CarroCompraDao {

    @Query("SELECT * FROM carro_compras")
    suspend fun getCarroCompraDb(): List<CarroCompraEntity>

}