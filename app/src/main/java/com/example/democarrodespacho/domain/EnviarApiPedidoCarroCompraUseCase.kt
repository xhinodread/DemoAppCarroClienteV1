package com.example.democarrodespacho.domain

import android.content.Context
import android.util.Log
import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.domain.model.Pedidos
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class EnviarApiPedidoCarroCompraUseCase @Inject constructor(
    private val repository: ResponseProductoRepository,
    private val pedidosAux: PedidosAux
) {
    suspend operator fun invoke(listadoPedido: List<Pedidos>): String {
        /*** ENVIAR PEDIDO AL API CON METODO POST ***/
        val cuerpoPost = pedidosAux.cuerpoPostApi(listadoPedido)
        //Log.d("onCreate", "okhttp3:" + cuerpoPost.toString())
        val envioPost = repository.setEnviarPedidoCarroComprasToApi(cuerpoPost)

        return envioPost
    }
}