package com.example.democarrodespacho.data.model

import com.google.gson.annotations.SerializedName

data class CarroCompraModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("producto") val producto: String?,
    @SerializedName("fecha") val fecha: String?
 )
