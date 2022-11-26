package com.example.democarrodespacho.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.democarrodespacho.MvvDemoDespachoApp
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.domain.*
import com.example.democarrodespacho.domain.model.Pedidos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarroCompraViewModel @Inject constructor(
    private val pedidoCarroCompraDbUseCase: GetPedidoCarroCompraDbUseCase,
    private val getProductoCarroCompraDbUseCase: GetProductoCarroCompraDbUseCase,
    private val setProductoCarroCompraUseCase: SetProductoCarroCompraUseCase,
    private val getTotalCarroCompraDbUseCase: GetTotalCarroCompraDbUseCase,
    private val setDeletProdItemCarroCompraUseCase: SetDeletProdItemCarroCompraUseCase,
    private val setDeletTodoProdCarroCompraUseCase: SetDeletTodoProdCarroCompraUseCase,
    private val enviarWhatAppPedidoCarroUseCase: EnviarWhatAppPedidoCarroUseCase,
    private val enviarApiPedidoCarroCompraUseCase: EnviarApiPedidoCarroCompraUseCase,
    private val application: MvvDemoDespachoApp
): ViewModel(){

    val listPedidoCarroCompraModel = MutableLiveData<List<Pedidos?>>()
    val totalPedidoCarroCompraModel = MutableLiveData<Int?>()
    val totalPedidoCarroCompra = MutableLiveData<Int?>()
    val productoCarroCompraModel = MutableLiveData<Pedidos?>()
    val isLoading = MutableLiveData<Boolean>()

    fun agregarProductoAlCarro(elProducto: CarroCompraEntity){
        viewModelScope.launch {
            isLoading.postValue(true)
            setProductoCarroCompraUseCase(elProducto)
            getProductoDesdeCarroCompraDb(elProducto.producto.toString())
         //   getTotalCarroCompraDb()
            isLoading.postValue(false)
        }
//        viewModelScope.launch {
//            getTotalCarroCompraDb()
//        }
    }
    fun listPedidoCarroCompraDb(){
//      Log.d("onCreate", "CarroCompraViewModel, listPedidoCarroCompraDb : "+ aPagar.toString())
        viewModelScope.launch {
            isLoading.postValue(true)
            val listPedidoCarroCompra = pedidoCarroCompraDbUseCase()
            if (!listPedidoCarroCompra.isNullOrEmpty()) {
                listPedidoCarroCompraModel.postValue(listPedidoCarroCompra)
                val aPagar= listPedidoCarroCompra.sumOf { it.aPagar!! }
                totalPedidoCarroCompraModel.postValue(aPagar)
            }else{
                listPedidoCarroCompraModel.postValue(emptyList())
                totalPedidoCarroCompraModel.postValue(0)
            }
            isLoading.postValue(false)
        }
    }

    fun borrarItemProductoCarroCompraDb(elNombreProducto: String){
        //Log.d("onCreate", "CarroCompraViewModel, borrarItemProductoCarroCompra : "+ elNombreProducto)
        viewModelScope.launch {
             setDeletProdItemCarroCompraUseCase(elNombreProducto)
             listPedidoCarroCompraDb()
        }
    }
    fun borrarUnItemProductoCarroCompraDb(elNombreProducto: String){
        //Log.d("onCreate", "CarroCompraViewModel, borrarItemProductoCarroCompra : "+ elNombreProducto)
        viewModelScope.launch {
            setDeletProdItemCarroCompraUseCase(elNombreProducto)
            getProductoDesdeCarroCompraDb(elNombreProducto)
        }
    }
    fun borrarTodoProductoCarroCompraDb(elNombreProducto: String){
        //Log.d("onCreate", "CarroCompraViewModel, borrarItemProductoCarroCompra : "+ elNombreProducto)
        viewModelScope.launch {
            setDeletTodoProdCarroCompraUseCase(elNombreProducto)
            getProductoDesdeCarroCompraDb(elNombreProducto)
        }
    }
    fun getProductoDesdeCarroCompraDb(elNombreProducto: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            productoCarroCompraModel.postValue(Pedidos(null, 0, "", 0,0 , ""))
            val elProducto = getProductoCarroCompraDbUseCase(elNombreProducto)
            //Log.d("onCreate", "listProductos notNull: "+ elProducto.toString())
            if (elProducto != null) {
                productoCarroCompraModel.postValue(elProducto)
            }
            isLoading.postValue(false)
        }
    }
    fun getTotalCarroCompraDb(){
        viewModelScope.launch {
            isLoading.postValue(true)
            totalPedidoCarroCompra.postValue(totalPedidoCarroCompra.value)
            val totalCarroCompra = getTotalCarroCompraDbUseCase() ?: 0
            totalPedidoCarroCompra.postValue(totalCarroCompra)
            isLoading.postValue(false)
        }
    }
    fun enviarPedidoWhatApp(context: Context){
        viewModelScope.launch {
            val listPedidoCarroCompra = pedidoCarroCompraDbUseCase()
            var mensaje = PedidosAux().crearMensajeWs(listPedidoCarroCompra)
            mensaje += "\n Cliente Rut: 12345678-0 Chile Aracena Coquimbo"
            enviarWhatAppPedidoCarroUseCase(mensaje, context)
        }
    }

    fun enviarPedidoPostApi(){
        viewModelScope.launch {
            val listPedidoCarroCompra = pedidoCarroCompraDbUseCase()
            enviarApiPedidoCarroCompraUseCase(listPedidoCarroCompra)
        }
    }



}