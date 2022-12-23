package com.example.democarrodespacho.data.model

import com.google.gson.annotations.SerializedName

data class ClienteModel(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("rut") val rut: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("direccion") val direccion: String?
)
