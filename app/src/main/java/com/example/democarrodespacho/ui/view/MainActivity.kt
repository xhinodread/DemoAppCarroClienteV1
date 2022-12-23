package com.example.democarrodespacho.ui.view

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.democarrodespacho.R
import com.example.democarrodespacho.domain.PedidosAux
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.data.database.entities.ClienteEntity
//import com.example.democarrodespacho.data.dto.*
import com.example.democarrodespacho.databinding.ActivityMainBinding
import com.example.democarrodespacho.databinding.FragmentCarroCompraBinding
import com.example.democarrodespacho.databinding.ItemMenuAcercaBinding
import com.example.democarrodespacho.databinding.ItemMenuMicuentaBinding
import com.example.democarrodespacho.databinding.ItemPopupProductoBinding
import com.example.democarrodespacho.databinding.ItemPopupErrorBinding
import com.example.democarrodespacho.domain.model.Cliente
//import com.example.democarrodespacho.domain.LlamarPostApi
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.ui.adapter.CarroComprasRecyVAdapter
//import com.example.democarrodespacho.ui.adapter.CarroComprasRecyVAdapter
import com.example.democarrodespacho.ui.adapter.ProductoAdapter
import com.example.democarrodespacho.ui.viewmodel.CarroCompraViewModel
import com.example.democarrodespacho.ui.viewmodel.ClienteViewModel
import com.example.democarrodespacho.ui.viewmodel.ProductoViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.util.*
import kotlin.math.log

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
//    class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    /**
     * FALTA IMPLEMENTAR, TEST UNITARIOS
     */
    private lateinit var binding: ActivityMainBinding
    private lateinit var popupBindingProd : ItemPopupProductoBinding
    private lateinit var popupCarroCompraBinding: FragmentCarroCompraBinding
    private lateinit var popupBindingMiCuenta: ItemMenuMicuentaBinding
//    private lateinit var popupBindingAcercade: ItemMenuAcercaBinding
    private lateinit var popupItemPopupErrorBinding: ItemPopupErrorBinding
    private lateinit var adapter: CarroComprasRecyVAdapter
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private val productoViewModel: ProductoViewModel by viewModels()
    private val carroComprasViewModel: CarroCompraViewModel by viewModels()
    private val clienteViewModel: ClienteViewModel by viewModels()
    private val pedidosAux by lazy { PedidosAux() }

    private val swDB: Boolean = !true // false:API - true:ProviderLocalApp

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*************** BarDrawer ********************/
        binding.navView.setNavigationItemSelectedListener{
            setNavigationItemSelectedListener(it)
            true
        }
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        // pass the Open and Close toggle for the drawer layout listener to toggle the button
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /***********************************/

        productoViewModel.isLoading.observe(this) { estadoObserver ->
            binding.loading.isVisible = estadoObserver
        }
        carroComprasViewModel.pedidoCargado.observe(this){
            if(it=="true"){
                pedidosAux.crearAlert(popupCarroCompraBinding, "Envio realizado exitoso")
            }else if(it=="false") {
                pedidosAux.crearAlert(popupCarroCompraBinding, "Ocurrio un error al enviar su solicitud !!!")
            }
        }

        cargarReciclerViewProductos()
        initFabButtonCarroCompra()

        clienteViewModel.clienteModel.observe(this){ elCliente ->
            Log.d("onCreate", "elCliente: "+ elCliente?.toString())
            popupBindingMiCuenta.edtxNombre.setText(elCliente?.nombre)
            popupBindingMiCuenta.edtxRut.setText(elCliente?.rut)
            popupBindingMiCuenta.edtxEmail.setText(elCliente?.email)
            popupBindingMiCuenta.edtxDireccion.setText(elCliente?.direccion)
        }
    }

    private fun cargarReciclerViewProductos(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.rvProductos.layoutManager = manager
       // binding.rvProductos.addItemDecoration(decoration)

        /**
         * EN ESTE LUGAR SE PROPONE IMPLEMENTAR EL CONTROL DE ACCESO SEGUN LA DISPONIBLILIDAD DE
         * LA RED Y/O API SRVER
         */
        if(swDB){
            productoViewModel.listAllProductosDb() // binding.rvProductos.adapter  = ProductoAdapter(ProductoProvider.productoList) {  }
        }else{
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
        //Toast.makeText(this, "Eliminando $nombreProducto", Toast.LENGTH_SHORT).show()
        carroComprasViewModel.borrarItemProductoCarroCompraDb(nombreProducto)
    }
    private fun enviarSolicitudPedidoCarroCompra(){
        //Toast.makeText(this, "enviarSolicitud", Toast.LENGTH_SHORT).show()
        if(!false)carroComprasViewModel.enviarPedidoWhatApp(this)
        if(false) {
            carroComprasViewModel.enviarPedidoPostApi()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        posicionMenu()
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
    private fun setNavigationItemSelectedListener(menuItem: MenuItem){
        this.toast(menuItem.toString())
        val elBinding = when(menuItem.toString()){
            "My Cuenta" -> {
                popupBindingMiCuenta = ItemMenuMicuentaBinding.inflate(layoutInflater)
                setMyCuenta()
                popupBindingMiCuenta
            } else -> {
                // "Acerca de"
                ItemMenuAcercaBinding.inflate(layoutInflater)
            }
        }
        if(menuItem.toString() != "Salir")pedidosAux.crearAlertPopup(elBinding)
    }

     fun setMyCuenta(){
       // popupBindingMiCuenta.edtxNombre.text = ""
         this.clienteViewModel.gelDbCliente()
         popupBindingMiCuenta.btnGuardar.setOnClickListener {
             val rut = popupBindingMiCuenta.edtxRut.text.toString()
             val nombre = popupBindingMiCuenta.edtxNombre.text.toString()
             val email = popupBindingMiCuenta.edtxEmail.text.toString()
             val direccion = popupBindingMiCuenta.edtxDireccion.text.toString()
             clienteViewModel.borrarClienteDb()
             clienteViewModel.agregarCliente(
                 ClienteEntity(0, nombre, rut, email, direccion)
             )
         }
    }

    private fun posicionMenu(){
        val handler = Handler()
        if( binding.myDrawerLayout.elevation == binding.txtViewSearch.elevation ){
            binding.myDrawerLayout.elevation = binding.myDrawerLayout.elevation +1
        }else if( binding.myDrawerLayout.elevation > binding.txtViewSearch.elevation ){
            handler.postDelayed({
                binding.myDrawerLayout.elevation = binding.txtViewSearch.elevation -1
            }, 500)
        }else{
            binding.myDrawerLayout.elevation = binding.txtViewSearch.elevation +1
        }
    }
    private fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

