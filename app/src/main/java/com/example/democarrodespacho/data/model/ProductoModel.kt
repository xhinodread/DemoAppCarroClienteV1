package com.example.democarrodespacho.data.model

import com.google.gson.annotations.SerializedName

data class ProductoModel(
    @SerializedName("id") val id: Int ,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("precio") val precio: Int,
    @SerializedName("foto") val foto: String
)