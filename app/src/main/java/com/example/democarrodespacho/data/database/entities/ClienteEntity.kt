package com.example.democarrodespacho.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.democarrodespacho.domain.model.Cliente

@Entity(tableName = "clientes")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int=0,
    @ColumnInfo(name = "nombre") val nombre: String?,
    @ColumnInfo(name = "rut") val rut: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "direccion") val direccion: String?
)
fun Cliente.toDatabase() = ClienteEntity(nombre = nombre, rut =  rut, email = email, direccion = direccion)