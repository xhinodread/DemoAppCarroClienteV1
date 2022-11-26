package com.example.democarrodespacho.data.model

import com.google.gson.annotations.SerializedName

data class ResponseProductoModel(
    @SerializedName("msg") val msg: String,
    @SerializedName("data") val data: List<ProductoModel>
)
