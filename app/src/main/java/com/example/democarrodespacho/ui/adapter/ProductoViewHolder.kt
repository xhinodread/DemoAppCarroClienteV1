package com.example.democarrodespacho.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.democarrodespacho.domain.PedidosAux
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.databinding.ItemProductoBinding

class ProductoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemProductoBinding.bind(view)

    @SuppressLint("CheckResult")
    fun render(productoModel: Producto, onClickListener: (Producto)->Unit){
        val precioProducto = PedidosAux().formatNumber(productoModel.precio.toString())
        binding.tvSueperHeroName.text = productoModel.nombre
        binding.tvSueperHeroRealName.text = " $ $precioProducto"
        binding.tvPublisher.text = "OFERTA!!!"
        Glide.with(binding.ivSuperHero.context).load(productoModel.foto).into(binding.ivSuperHero)
        itemView.setOnClickListener{
            onClickListener(productoModel)
        }
    }
}