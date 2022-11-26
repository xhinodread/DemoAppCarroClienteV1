package com.example.democarrodespacho.data.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PedidosModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("cantidad") val cantidad: Int?,
    @SerializedName("nombre_producto") val nombre_producto: String?,
    @SerializedName("precio") val precio: Int?,
    @SerializedName("a_pagar") val aPagar: Int?,
    @SerializedName("foto") val foto: String?,
)
