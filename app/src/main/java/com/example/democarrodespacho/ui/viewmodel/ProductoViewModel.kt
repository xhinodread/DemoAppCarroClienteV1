package com.example.democarrodespacho.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.democarrodespacho.domain.GetProductosApiUseCase
import com.example.democarrodespacho.domain.model.Producto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.democarrodespacho.domain.GetAllProductosDbUseCase
import com.example.democarrodespacho.domain.GetFiltroProductosDbUseCase
import com.example.democarrodespacho.domain.GetProductoDbUseCase

@HiltViewModel
class ProductoViewModel @Inject constructor(
    private val getProductosApiUseCase: GetProductosApiUseCase,
    private val getAllProductosDbUseCase: GetAllProductosDbUseCase,
    private val getProductoDbUseCase: GetProductoDbUseCase,
    private val getFiltroProductosDbUseCase: GetFiltroProductosDbUseCase
): ViewModel() {

    val productoModel = MutableLiveData<Producto?>()
    var listProductoModel = MutableLiveData<List<Producto?>?>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getProductosApiUseCase()
            if (!result.isNullOrEmpty()) {
                 //Log.d("onCreate", "result: "+ result.toString())
                listProductoModel.postValue(result)
            }else{
                listProductoModel.postValue(emptyList())
            }
            isLoading.postValue(false)
        }
    }
    fun onCreateCallApiProductos() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getProductosApiUseCase()
            if (!result.isNullOrEmpty()) {
                //Log.d("onCreate", "result: "+ result.toString())
                listProductoModel.postValue(result)
            }else{
                listProductoModel.postValue(emptyList())
            }
            isLoading.postValue(false)
        }
    }

    fun listAllProductosDb(){
        viewModelScope.launch {
            // isLoading.postValue(true)
            val listProductos = getAllProductosDbUseCase()
            //Log.d("onCreate", "listProductos notNull: "+ listProductos.toString())
            if (!listProductos.isNullOrEmpty()) {
                listProductoModel.postValue(listProductos)
            }
            //listQuoteModel.postValue(emptyList())
            //  isLoading.postValue(false)
        }
    }

    fun getProductoDb(nombre: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            productoModel.postValue(null)
            val elProducto = getProductoDbUseCase(nombre)
            //Log.d("onCreate", "listProductos notNull: "+ elProducto.toString())
            if (elProducto != null) {
                productoModel.postValue(elProducto)
            }
            //productoModel.postValue(null)
            isLoading.postValue(false)
        }
    }

    fun getFiltroProductoDb(nombreProducto: String){
        viewModelScope.launch {
            val listProductos = getFiltroProductosDbUseCase(nombreProducto)
            //Log.d("onCreate", "listProductos notNull: "+ listProductos.toString())
            if (!listProductos.isNullOrEmpty()) {
                listProductoModel.postValue(listProductos)
            }

        }
    }

    fun getFilteredList(s: String): LiveData<List<Producto?>> {
        Log.d("onCreate", "getFilteredList: " + s.toString())
        return Transformations.map(listProductoModel) {
            Log.d("onCreate", "getFilteredList: " + it + "  " + s.toString())
            it?.filter {
                it?.nombre.toString().contains(s)
               // it.name.contains(s)
            }
        }
    }

}
//
//sealed class MovieState {
//    object START : MovieState()
//    object LOADING : MovieState()
//    object SUCCESS : MovieState()
//    data class FAILURE(val message: String) : MovieState()
//}
