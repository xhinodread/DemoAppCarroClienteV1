package com.example.democarrodespacho.ui.view
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import androidx.core.widget.addTextChangedListener
//import androidx.lifecycle.lifecycleScope
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bumptech.glide.Glide
//import com.example.democarrodespacho.domain.Network
//import com.example.democarrodespacho.domain.PedidosAux
//import com.example.democarrodespacho.data.ProductoProvider
////import com.example.democarrodespacho.data.dto.*
//import com.example.democarrodespacho.databinding.ActivityMainBinding
//import com.example.democarrodespacho.databinding.FragmentCarroCompraBinding
//import com.example.democarrodespacho.databinding.ItemPopupBinding
////import com.example.democarrodespacho.domain.LlamarPostApi
//import com.example.democarrodespacho.domain.model.Producto
//import com.example.democarrodespacho.ui.MainAux
////import com.example.democarrodespacho.ui.adapter.CarroComprasRecyVAdapter
//import com.example.democarrodespacho.ui.adapter.ProductoAdapter
//import kotlinx.coroutines.*
//import okhttp3.RequestBody
//import java.util.*
//
//class MainActivity_: AppCompatActivity() {
////
////    private lateinit var binding: ActivityMainBinding
////    private lateinit var popupBindingProd : ItemPopupBinding
////    private lateinit var popupCarroCompraBinding: FragmentCarroCompraBinding
////    private lateinit var adapter: CarroComprasRecyVAdapter
////
////    private val carroCompraDataBase by lazy {
////        CarroCompraDataBase.getDatabase(this).carroCompraDao()
////    }
////    private val productoDataBase by lazy {
////        CarroCompraDataBase.getDatabase(this).productosDao()
////    }
////    private val pedidosAux by lazy { PedidosAux() }
////    private val llamarPostApi by lazy { LlamarPostApi() }
////    private val productoProvider by lazy { ProductoProvider }
////
////    private val mainAux = MainAux()
////    private val apiProcess = ApiProcess()
////
////    lateinit var productoAdapter: ProductoAdapter
////
////    private val swDB:Boolean = !true // false:API - true:ProviderLocalApp
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        binding = ActivityMainBinding.inflate(layoutInflater)
////        super.onCreate(savedInstanceState)
////        setContentView(binding.root)
////
////        productoAdapter = ProductoAdapter(emptyList()){}
////
////        if(!swDB)apiProcess.getProductos()
////        initSearchButton() //MainAux().initSearchButton(binding)
////        initRecyclerViewProductos()
////        initFabButtonCarroCompra()
////    }
////
////    private fun initSearchButton(){
////        lifecycleScope.launch {
////            CoroutineScope(Dispatchers.IO).launch {
////                val listadoProductoProvider: MutableList<Producto> = productoDataBase.getProductosDb() as MutableList<Producto> // productoProvider.productoList
////                binding.txtViewSearch.addTextChangedListener { texto ->
////                    val filtroProductoProvider = mainAux.filtroProductoProvider(listadoProductoProvider, texto.toString()) //listadoProductoProvider.filter{ valor -> pedidosAux.filtroBuscador( valor, texto.toString() ) }
////                    binding.miRecyclerView.adapter = ProductoAdapter(filtroProductoProvider) { productos ->
////                            cargarPopUpProducto(productos)
////                    }
////                }
////            }
////        }
////    }
////
////    private fun initRecyclerViewProductos(){
////        val manager = LinearLayoutManager(this) // DISEÑO LINEAL
////        val decoration = DividerItemDecoration(this, manager.orientation)
////        binding.miRecyclerView.layoutManager = manager
////        binding.miRecyclerView.addItemDecoration(decoration)
////        if(swDB)binding.miRecyclerView.adapter = ProductoAdapter(productoProvider.productoList) { productos -> cargarPopUpProducto(productos) }
////        else observeProductos()
////
////    }
////    private fun observeProductos() {
////        lifecycleScope.launch {
////            productoDataBase.getProductos().collect{ productosList ->
////                //Log.d("observeProductos", productosList.toString())
////                if(productosList.isNotEmpty()) {
////                    binding.miRecyclerView.adapter = ProductoAdapter(productosList) { productos -> cargarPopUpProducto(productos) }
////                }else{
////                    binding.miRecyclerView.adapter = ProductoAdapter(emptyList()) { productos -> cargarPopUpProducto(productos) }
////                }
////            }
////        }
////    }
////    private fun cargarPopUpProducto(elProducto: Producto){
////        popupBindingProd = ItemPopupBinding.inflate(layoutInflater)
////        pedidosAux.crearAlertPopup(popupBindingProd)
////        val precioProducto = pedidosAux.formatNumber(elProducto.precio.toString())
////        popupBindingProd.tvProductoName.text = "${elProducto.nombre} $ $precioProducto"
////        Glide.with(popupBindingProd.ivSuperHeroPopup.context).load(elProducto.foto).into(popupBindingProd.ivSuperHeroPopup)
////        buscarProdCarro(elProducto.nombre.toString(), elProducto.precio)
////
////        popupBindingProd.btnAgregarItem.setOnClickListener{
////            //Toast.makeText(this, nuevoItem.toString(), Toast.LENGTH_SHORT).show()
////            lifecycleScope.launch {
////                carroCompraDataBase.addCarroCompra( CarroCompra(null, elProducto.nombre.toString(), Date() ) )
////                buscarProdCarro(elProducto.nombre.toString(), elProducto.precio)
////            }
////        }
////
////        popupBindingProd.btnBorrarItem.setOnClickListener{
////            val borrarItemNombre = elProducto.nombre.toString()
////            lifecycleScope.launch {
////                carroCompraDataBase.delCarroCompra(borrarItemNombre)
////                buscarProdCarro(borrarItemNombre, elProducto.precio)
////            }
////        }
////
////        popupBindingProd.btnBorrarUnItem.setOnClickListener {
////            lifecycleScope.launch {
////                carroCompraDataBase.deleteUnItem(elProducto.nombre.toString())
////                buscarProdCarro(elProducto.nombre.toString(), elProducto.precio)
////            }
////        }
////    }
////    private fun buscarProdCarro(nombreProducto: String, precioProducto:Int=0){
////        lifecycleScope.launch {
////            withContext(Dispatchers.IO) {
////                val totalProducto = carroCompraDataBase.countProdCarroCompras(nombreProducto)
////                val subTot = pedidosAux.subTotalProducto(precioProducto, totalProducto)
////                withContext(Dispatchers.Main) {
////                    popupBindingProd.tvCantCarro.text = "En carro $totalProducto ${subTot}"
////                }
////            }
////        }
////    }
////
////    private fun initFabButtonCarroCompra(){
////        binding.fab.setOnClickListener{
////            //Toast.makeText(this, "click en FAB", Toast.LENGTH_SHORT).show()
////            cargarPopUpCarro()
////        }
////    }
////    private fun cargarPopUpCarro(){
////        popupCarroCompraBinding = FragmentCarroCompraBinding.inflate(layoutInflater)
////        pedidosAux.crearPopUpCarro(popupCarroCompraBinding) { enviarSolicitud() }
////        setCarroRecyclerView(popupCarroCompraBinding)
////        observeCarroCompras()
////    }
////    private fun enviarSolicitud(){
////        val isConected = Network.isConnected(this@MainActivity_)
////        if(isConected){
////            Toast.makeText(this,"...Enviando solicitud...", Toast.LENGTH_SHORT).show()
////            lifecycleScope.launch {
////                val parcialEnvio: MutableList<String> = mutableListOf()
////                val envioWs: MutableList<String> = mutableListOf()
////                withContext(Dispatchers.IO) {
////                    carroCompraDataBase.getAllCarroCompras().map{ itemCarro-> parcialEnvio.add(itemCarro.producto) } //consulta a RoomDb
////                    val parcialEnv = pedidosAux.parcialEnvio(parcialEnvio)
////                    envioWs.add(parcialEnv.toString())
////                    if(true) {
////                        // openWhatsAppSend(envioWs.joinToString("\n"))
////                        if(!pedidosAux.openWhatsAppSend(envioWs.joinToString("\n"), this@MainActivity_)){
////                            withContext(Dispatchers.Main) {
////                                Toast.makeText(this@MainActivity_, "Error en WhatsApp", Toast.LENGTH_SHORT).show()
////                            }
////                        }
////                    }
////                    if(false) {
////                        //llamarPostApi(parcialEnvio) // ANTIWO
////                        val cabeceraEnvio = llamarPostApi.cuerpoPostApi(parcialEnvio)
////                        val elRetrofit = llamarPostApi.crearRetrofit("http://192.168.1.89")
////                        llamarPostApi(cabeceraEnvio, elRetrofit)
////                    }
////                }
////            }
////        }else{
////            Toast.makeText(this@MainActivity_, "No tiene conección a Internet \n Verifique su conección", Toast.LENGTH_LONG).show()
////        }
////    }
////    private fun llamarPostApi(requestBody: RequestBody, apiService: APIService){
////        CoroutineScope(Dispatchers.IO).launch {
////            // Do the POST request and get response
////            try {
////                val response = apiService.enviarSolicitudPedidos(requestBody)
////                //Log.d("llamarPostApi", "A "+ response.toString())
////                withContext(Dispatchers.IO) {
////                    if (response.isSuccessful) {
////                        val respString = response.body()?.string()
////                        //Log.d("llamarPostApi", "B "+ respString.toString())
////                        // FALTA IMPLEMENTAR QUE ELIMINE EL PEDIDO DE ROOM CUANDO LA API CONFIRME LA RECEPCION DEL PEDIDO
////                        withContext(Dispatchers.Main) {
////                            Toast.makeText(
////                                this@MainActivity_,
////                                "Enviado al API...",
////                                Toast.LENGTH_SHORT
////                            ).show()
////                        }
////                    } else {
////                        //Log.e("llamarPostApi", "C "+ response.code().toString())
////                        withContext(Dispatchers.Main) {
////                            Toast.makeText(
////                                this@MainActivity_,
////                                "Error en remote api\n" + response.code().toString(),
////                                Toast.LENGTH_SHORT
////                            ).show()
////                        }
////                    }
////                }
////            }catch (e: Exception){
////                withContext(Dispatchers.Main){
////                    Toast.makeText(this@MainActivity_, "Error en remote api\n"+e.toString(), Toast.LENGTH_SHORT).show()
////                }
////                //Log.d("Error", "Error en remote api" + e.toString())
////            }
////        }
////    }
////    private fun setCarroRecyclerView(fragment: FragmentCarroCompraBinding){
////        adapter = CarroComprasRecyVAdapter()
////        fragment.recyclerViewShoppingCar.adapter = adapter
////    }
////    private fun observeCarroCompras() {
////        lifecycleScope.launch {
////            carroCompraDataBase.getCarroComprasAgrupado().collect{ carroComprasList ->
////                if(carroComprasList.isNotEmpty()) {
////                    val subtotalPedido = pedidosAux.calculaSubtotalPedidos(carroComprasList)
////                    val numeroFormateado = pedidosAux.formatNumber(subtotalPedido)
////                    popupCarroCompraBinding.tvCarroTotal.text = "A pagar: $$numeroFormateado "
////                    adapter.submitList(carroComprasList)
////                }else{
////                    adapter.submitList(emptyList())
////                }
////            }
////        }
////    }
//
// }