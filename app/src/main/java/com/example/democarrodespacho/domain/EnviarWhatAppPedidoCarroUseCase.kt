package com.example.democarrodespacho.domain

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import javax.inject.Inject

class EnviarWhatAppPedidoCarroUseCase @Inject constructor(
    private val pedidosAux: PedidosAux
){
    suspend operator fun invoke(mensaje: String, context: Context) {
       // val msg = pedidosAux.openWhatsAppSend("", )
        pedidosAux.openWhatsAppSend(mensaje, context)
    }
}