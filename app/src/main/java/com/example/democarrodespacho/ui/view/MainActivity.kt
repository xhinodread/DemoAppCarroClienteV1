package com.example.democarrodespacho.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.democarrodespacho.domain.PedidosAux
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
//import com.example.democarrodespacho.data.dto.*
import com.example.democarrodespacho.databinding.ActivityMainBinding
import com.example.democarrodespacho.databinding.FragmentCarroCompraBinding
import com.example.democarrodespacho.databinding.ItemPopupProductoBinding
import com.example.democarrodespacho.databinding.ItemPopupErrorBinding
//import com.example.democarrodespacho.domain.LlamarPostApi
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.ui.adapter.CarroComprasRecyVAdapter
//import com.example.democarrodespacho.ui.adapter.CarroComprasRecyVAdapter
import com.example.democarrodespacho.ui.adapter.ProductoAdapter
import com.example.democarrodespacho.ui.viewmodel.CarroCompraViewModel
import com.example.democarrodespacho.ui.viewmodel.ProductoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    /**
     * FALTA IMPLEMENTAR, TEST UNITARIOS
     */
    private lateinit var binding: ActivityMainBinding
    private lateinit var popupBindingProd : ItemPopupProductoBinding
    private lateinit var popupCarroCompraBinding: FragmentCarroCompraBinding
    private lateinit var popupItemPopupErrorBinding: ItemPopupErrorBinding
    private lateinit var adapter: CarroComprasRecyVAdapter

    private val productoViewModel: ProductoViewModel by viewModels()
    private val carroComprasViewModel: CarroCompraViewModel by viewModels()

    private val pedidosAux by lazy { PedidosAux() }
    private val swDB:Boolean = !true // false:API - true:ProviderLocalApp

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        productoViewModel.isLoading.observe(this) { estadoObserver ->
            binding.loading.isVisible = estadoObserver
        }

        cargarReciclerViewProductos()
        initFabButtonCarroCompra()
    }

    private fun cargarReciclerViewProductos(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.rvProductos.layoutManager = manager
        binding.rvProductos.addItemDecoration(decoration)

        /**
         * EN ESTE LUGAR SE PROPONE IMPLEMENTAR EL CONTROL DE ACCESO SEGUN LA DISPONIBLILIDAD DE
         * LA RED Y/O API SRVER
         */
        if(swDB){
            productoViewModel.listAllProductosDb() // binding.rvProductos.adapter  = ProductoAdapter(ProductoProvider.productoList) {  }
        }else {
//            productoViewModel.onCreate()
            productoViewModel.onCreateCallApiProductos()
        }
        productoViewModel.listProductoModel.observe(this) { listaProductos ->
            //Log.d("onCreate", "--> " + listaProductos.toString())
            if(!listaProductos.isNullOrEmpty()) {
                binding.rvProductos.adapter =
                    ProductoAdapter(listaProductos as List<Producto>) { productos -> cargarPopUpProducto(productos) }
            }else{
                //Toast.makeText(this, "Error en la conexion", Toast.LENGTH_SHORT).show()
                popupItemPopupErrorBinding = ItemPopupErrorBinding.inflate(layoutInflater)
                pedidosAux.crearAlertPopup(popupItemPopupErrorBinding)
                popupItemPopupErrorBinding.tvTexto.text = "Problemas en la conexion..."
//                pedidosAux.popUpMensajes(this, "ATENCION", "Problemas en la conexion...\n Carga de datos locales realizada.")
                productoViewModel.listAllProductosDb()
            }
        }

        initSearchButton()

    }
    private fun cargarPopUpProducto(elProducto: Producto){
        // Log.d("onCreate", "click: " + elProducto.toString())
        val precioProducto = pedidosAux.formatNumber(elProducto.precio.toString())
        popupBindingProd = ItemPopupProductoBinding.inflate(layoutInflater)
        pedidosAux.crearAlertPopup(popupBindingProd)
        popupBindingProd.tvProductoName.text = "${elProducto.nombre}\n $ $precioProducto"
        Glide.with(popupBindingProd.ivSuperHeroPopup.context).load(elProducto.foto).into(popupBindingProd.ivSuperHeroPopup)

        //carroComprasViewModel.listPedidoCarroCompraDb()
        carroComprasViewModel.getTotalCarroCompraDb()
        carroComprasViewModel.totalPedidoCarroCompra.observe(this){ totalCarro ->
            //Log.d("onCreate", "totalPedidoCarroCompraModel: " + totalCarro.toString())
            if(totalCarro != null) {
                popupBindingProd.tvAPagarCarro.text =
                    "A Pagar:    $" + pedidosAux.formatNumber(totalCarro.toString()) + ".-"
            }
        }

        carroComprasViewModel.getProductoDesdeCarroCompraDb(elProducto.nombre.toString())
        carroComprasViewModel.productoCarroCompraModel.observe(this){elProductoCCompra ->
            //Log.d("onCreate", "cargarPopUpProducto: " + elProductoCCompra.toString())
            if(elProductoCCompra != null){
                val subTot = pedidosAux.subTotalProducto(elProducto.precio ?: 0,elProductoCCompra.cantidad ?: 0)
                popupBindingProd.tvCantCarro.text = "En carro ${elProductoCCompra.cantidad} ${subTot}"
            }
        }

        popupBindingProd.btnAgregarItem.setOnClickListener{
            //Toast.makeText(this, nuevoItem.toString(), Toast.LENGTH_SHORT).show()
            carroComprasViewModel.agregarProductoAlCarro(CarroCompraEntity(null, elProducto.nombre.toString(), Date().toString() ))
           //
            //carroComprasViewModel.listPedidoCarroCompraDb()
            carroComprasViewModel.getTotalCarroCompraDb()
        }

        popupBindingProd.btnBorrarUnItem.setOnClickListener{
            //Toast.makeText(this,"btnBorrarUnItem", Toast.LENGTH_SHORT).show()
            carroComprasViewModel.borrarUnItemProductoCarroCompraDb(elProducto.nombre.toString())
            //carroComprasViewModel.listPedidoCarroCompraDb()
            carroComprasViewModel.getTotalCarroCompraDb()
        }

        popupBindingProd.btnBorrarItem.setOnClickListener{
            //Toast.makeText(this,"btnBorrarItem", Toast.LENGTH_SHORT).show()
            carroComprasViewModel.borrarTodoProductoCarroCompraDb(elProducto.nombre.toString())
            //carroComprasViewModel.listPedidoCarroCompraDb()
            carroComprasViewModel.getTotalCarroCompraDb()
        }

    }
    private fun initSearchButton(){
        binding.txtViewSearch.addTextChangedListener { text ->
            productoViewModel.getFiltroProductoDb(text.toString())
        }

    }

    private fun initFabButtonCarroCompra(){
        adapter = CarroComprasRecyVAdapter(){ hacerEliminarItemProductoCarro(it) }
        carroComprasViewModel.listPedidoCarroCompraModel.observe(this){ listadoPedidoCarro ->
            //Log.d("onCreate", "listadoPedidoCarro: " + listadoPedidoCarro.toString() )
            adapter.submitList(listadoPedidoCarro)
            //adapter = CarroComprasRecyVAdapter()
        }
        binding.fab.setOnClickListener{
            cargarPopUpCarro()
        }
    }
    private fun cargarPopUpCarro(){
        popupCarroCompraBinding = FragmentCarroCompraBinding.inflate(layoutInflater)
        pedidosAux.crearPopUpCarro(popupCarroCompraBinding){ enviarSolicitudPedidoCarroCompra() }
        carroComprasViewModel.listPedidoCarroCompraDb()
        adapter = CarroComprasRecyVAdapter(){ hacerEliminarItemProductoCarro(it) }
        popupCarroCompraBinding.recyclerViewShoppingCar.adapter = adapter
        carroComprasViewModel.totalPedidoCarroCompraModel.observe(this){ totalCarro ->
            //Log.d("onCreate", "totalCarro: "+ totalCarro)
            popupCarroCompraBinding.tvCarroTotal.text = "A Pagar:    $"+pedidosAux.formatNumber(totalCarro.toString()) + ".-"
        }
    }
    private fun hacerEliminarItemProductoCarro(nombreProducto: String){
        Toast.makeText(this, "Eliminando $nombreProducto", Toast.LENGTH_SHORT).show()
        carroComprasViewModel.borrarItemProductoCarroCompraDb(nombreProducto)
    }
    private fun enviarSolicitudPedidoCarroCompra(){
        //Toast.makeText(this, "enviarSolicitud", Toast.LENGTH_SHORT).show()
        if(!false)carroComprasViewModel.enviarPedidoWhatApp(this)
        if(false)carroComprasViewModel.enviarPedidoPostApi()
    }

 }

