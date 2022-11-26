package com.example.democarrodespacho.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.databinding.ItemProductoBinding

class ProductoAdapter(private var productoList: List<Producto>,
                      private val onClickListener:(Producto)->Unit
                    ): RecyclerView.Adapter<ProductoViewHolder>() {
    private lateinit var itemProductoBinding : ItemProductoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemProductoBinding = ItemProductoBinding.inflate(layoutInflater, parent, false)
        return ProductoViewHolder(itemProductoBinding.root)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item = productoList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = productoList.size

    fun updateProductos(productoList: List<Producto>){
        this.productoList = productoList
    }

}