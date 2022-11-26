package com.example.democarrodespacho.ui.adapter

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.democarrodespacho.data.ProductoRepository
import com.example.democarrodespacho.data.database.CarroCompraDataBase
import com.example.democarrodespacho.domain.PedidosAux
//import com.example.democarrodespacho.data.dto.CarroCompraAgrupado
//import com.example.democarrodespacho.data.dto.CarroCompraDataBase
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.databinding.ItemCarroCompraBinding
import com.example.democarrodespacho.domain.model.Pedidos
import com.example.democarrodespacho.ui.viewmodel.ProductoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.times

class CarroComprasRecyVHolder(private val binding: ItemCarroCompraBinding): RecyclerView.ViewHolder(binding.root) {

    fun render(currentItem: Pedidos, onClickListener:(nombreItem: String)->Unit){
        //Log.d("render", "CarroComprasRecyVHolder")
        val precioProducto = PedidosAux().formatNumber(currentItem.precio.toString())
        val aPagarProducto = PedidosAux().formatNumber((currentItem.aPagar).toString())
        binding.tvCarroNombreProd.text ="${currentItem.cantidad} x ${currentItem.nombre_producto}."
        binding.tvCarroNombreProd.textSize = 22F
        binding.tvCarroPrecioProd.text = "$$precioProducto"
        binding.tvCarroPrecioProd.textSize = 29F
        binding.tvCarroSubtotalProd.text = "$$aPagarProducto"
        binding.tvCarroSubtotalProd.textSize = 32F
        Glide.with(binding.ivCarroPopup.context).load(currentItem.foto).into(binding.ivCarroPopup)

        binding.btnCarroBorrarItem.setOnClickListener{
            //Toast.makeText(binding.btnCarroBorrarItem.context, "borre ${currentItem.nombre_producto}", Toast.LENGTH_SHORT).show()
            if(currentItem.nombre_producto!!.isNotEmpty()){
                // hacerBorrar(currentItem.nombre_producto)
                onClickListener(currentItem.nombre_producto)
            }
        }
    }
//    private fun traerDatosProducto(currentItem: Pedidos, prodList: List<Producto>): Producto? {
//        val datosProducto = prodList.find { itemProd -> itemProd.nombre == currentItem.producto }
//        return datosProducto
//    }
    private fun hacerBorrar(nombreItem: String){
        CoroutineScope(Dispatchers.IO).launch {
         //   CarroCompraDataBase.getDatabase(binding.btnCarroBorrarItem.context).carroCompraDao().deleteUnItem(nombreItem)
        }
    }


}