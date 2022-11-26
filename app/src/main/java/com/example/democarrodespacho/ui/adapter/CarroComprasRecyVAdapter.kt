package com.example.democarrodespacho.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.democarrodespacho.data.ProductoProvider
//import com.example.democarrodespacho.data.dto.CarroCompraAgrupado
//import com.example.democarrodespacho.data.dto.CarroCompraDataBase
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.databinding.ItemCarroCompraBinding
import com.example.democarrodespacho.domain.model.Pedidos
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarroComprasRecyVAdapter(private val onClickListener:(nombreItem: String)->Unit): ListAdapter<Pedidos, CarroComprasRecyVHolder>(DiffCallback()) {
    private lateinit var binding: ItemCarroCompraBinding
    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroComprasRecyVHolder{
        binding = ItemCarroCompraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
    return CarroComprasRecyVHolder(binding)
    }
//
    override fun onBindViewHolder(holder: CarroComprasRecyVHolder, position: Int) {
        val currentItem = getItem(position)
        CoroutineScope(Dispatchers.IO).launch {
//            Log.d("render", "onBindViewHolder: " + listadoProductoProvider.toString())
            withContext(Dispatchers.Main) {
                //holder.render(currentItem, listadoProductoProvider)
                holder.render(currentItem, onClickListener)

            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Pedidos>() {
    override fun areItemsTheSame(oldItem: Pedidos, newItem: Pedidos) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Pedidos, newItem: Pedidos)= oldItem == newItem
}
