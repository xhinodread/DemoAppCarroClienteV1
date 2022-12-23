package com.example.democarrodespacho.domain

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.system.Os.accept
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.democarrodespacho.R
import com.example.democarrodespacho.data.ProductoProvider
import com.example.democarrodespacho.domain.model.Pedidos
//import com.example.democarrodespacho.data.ProductoProvider
//import com.example.democarrodespacho.data.dto.CarroCompraAgrupado
//import com.example.democarrodespacho.domain.model.Pedidos
import com.example.democarrodespacho.domain.model.Producto
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class PedidosAux @Inject constructor(){

//    fun addPedido(pedidos: ArrayList<Pedidos>): JSONArray {
//        var pedJson = JSONArray()
//        pedidos.forEach {
//            pedJson.put(
//                JSONArray()
//                    .put(it.id_p)
//                    .put(it.cantidad)
//            )
//        }
//        return pedJson
//    }

    fun <T> findDuplicates(values: List<T>): Set<T> {
        return values.groupingBy { it }.eachCount().filter { it.value > 1 }.keys
    }

    fun countDuplicates(values: List<String>): List<Pair<String, Int>> {
        return values.groupingBy { it }.eachCount().filter { it.value >= 1 }.toList()
    }

    fun crearAlert(binding: ViewBinding, mensaje:String ="", positiveBtn:Boolean= false){
        val dialogAlert= AlertDialog.Builder(binding.root.context)
        dialogAlert.setMessage(mensaje.toUpperCase())
            .setPositiveButton("Cerrar"){ dialog , _->
                dialog.dismiss()
            }
        dialogAlert.setIcon(R.drawable.ic_baseline_error_outline_24)
        dialogAlert.show()
    }

    fun crearAlertPopup(binding: ViewBinding, positiveBtn:Boolean= false){
        val dialogAlert= AlertDialog.Builder(binding.root.context)
        dialogAlert.setView(binding.getRoot())
            .setPositiveButton("Cerrar"){ dialog , _->
                dialog.dismiss()
            }
        dialogAlert.setIcon(R.drawable.ic_baseline_error_outline_24)
        dialogAlert.show()
    }

    fun popUpMensajes(context: Context, titulo: String="", mensaje:String=""){
        MaterialAlertDialogBuilder(context)
            .setTitle("     $titulo")
            .setMessage("!!! $mensaje ¡¡¡")
            .setNeutralButton("NADA") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("CANCELAR") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("ACEPTAR") { dialog, which ->
                // Respond to positive button press
            }
            .setIcon(R.drawable.ic_baseline_error_outline_24)
            .show()
    }

    fun crearPopUpCarro(binding: ViewBinding, onClickListenerPositive:()->Unit){
        val dialog= AlertDialog.Builder(binding.root.context)
        dialog.setView(binding.getRoot())
            .setNegativeButton("Cerrar"){ dialog,_->
                dialog.dismiss()
            }
            .setPositiveButton("Enviar"){dialog,_->
                onClickListenerPositive()
                //dialog.dismiss()
            }
        dialog.show()
    }

    fun parcialEnvio(listado: MutableList<String>): List<String>{
        val parcialEnv = listado.distinct().map{ ittem ->
            val cantItem = listado.filter { valor -> valor == ittem }
            "${cantItem.size} $ittem"
        }
        return parcialEnv
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

//    fun calculaSubtotalPedidos(carroComprasList: List<CarroCompraAgrupado>): String{
//        val listaProducto = ProductoProvider.productoList
//        val listaPedido = carroComprasList.map { itemCarro ->
//            val elProducto = listaProducto.find { itemProducto -> itemCarro.producto == itemProducto.nombre }
//            elProducto?.precio?.times(itemCarro.cantidad) ?: 0
//        }
//        return listaPedido.sum().toString()
//    }

    fun formatNumber(valor:String):String{
//                    val numberFormat = NumberFormat.getNumberInstance()// .getCurrencyInstance()
//                    numberFormat.setMaximumFractionDigits(0);
//                    val myNumber = numberFormat.format(calculaSubtotal.toInt())

        return NumberFormat.getNumberInstance(Locale.US)
            .format(valor.toInt())
            .replace(",", ".")
    }

    fun subTotalProducto(precio:Int, cantProducto:Int):String{
        if(cantProducto>0)return  ": $" + formatNumber((cantProducto * precio).toString())
        return "0"
    }

    fun filtroBuscador(elProducto: Producto, aBuscar:String): Boolean {
       return elProducto.nombre.toString().lowercase().contains(aBuscar.lowercase())
    }

    fun crearMensajeWs(listado: List<Pedidos?>): String{
        return listado.map { itemPedido -> "${itemPedido?.cantidad} ${itemPedido?.nombre_producto}, \n" }.toString()
    }

    fun openWhatsAppSendActivity(stringEnvio: String, activity: AppCompatActivity ): Boolean {
        try {
            val toNumber = "56968549743" // "56991648937" // Replace with mobile phone number without +Sign or leading zeros, but with country code
            val wsIntent = Uri.parse("http://api.whatsapp.com/send?phone=$toNumber&text=${stringEnvio}")
            val mapIntent = Intent(Intent.ACTION_VIEW, wsIntent)
            mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // Try to invoke the intent.
            try {
                activity?.applicationContext?.startActivity(mapIntent)
                return true
            } catch (e: ActivityNotFoundException) {
                Log.d("LOGWS", e.toString())
                return false
            }

        } catch (e: Exception) {
            Log.d("LOGWS", e.toString())
            return false
        }
    }
    fun openWhatsAppSend(stringEnvio: String, context: Context): Boolean {
        try {
            val toNumber = "56968549743" // "56991648937" // Replace with mobile phone number without +Sign or leading zeros, but with country code
            val wsIntent = Uri.parse("http://api.whatsapp.com/send?phone=$toNumber&text=${stringEnvio}")
            val mapIntent = Intent(Intent.ACTION_VIEW, wsIntent)
            mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // Try to invoke the intent.
            try {
                context.startActivity(mapIntent)
                return true
            } catch (e: ActivityNotFoundException) {
                Log.d("LOGWS", e.toString())
                return false
            }

        } catch (e: Exception) {
            Log.d("LOGWS", e.toString())
            return false
        }
    }
    fun cuerpoPostApi(listadoPedido: List<Pedidos>): RequestBody {
        // Create JSON using JSONObject
        val pedJson = JSONArray()
        listadoPedido.forEach { pedJson.put(JSONArray().put(it)) }
        //Log.d("enviarSolicitud pedJson", pedJson.toString() )
        val jsonObject = JSONObject()
        val jsonB = JSONObject()
        jsonB.put("id_cliente", "7890")
        jsonObject.put("pedido_app_android", jsonB)
        jsonObject.put("pedido", addPedido(listadoPedido as ArrayList<Pedidos>))
        //jsonObject.put("pedido", listadoPedido)
        //jsonObject.put("pedido", pedJson)
        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()
        Log.d("onCreate", "PedidosAux() - json: " + jsonObjectString )
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        return requestBody
    }
    private fun addPedido(pedidos: ArrayList<Pedidos>): JSONArray {
        val pedJson = JSONArray()
        pedidos.forEach {
            pedJson.put(
                JSONArray()
                    .put(it.nombre_producto)
                    .put(it.cantidad)
            )
        }
        return pedJson
    }

}