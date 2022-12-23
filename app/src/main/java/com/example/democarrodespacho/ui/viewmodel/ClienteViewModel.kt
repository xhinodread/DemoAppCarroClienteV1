package com.example.democarrodespacho.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.data.database.entities.ClienteEntity
import com.example.democarrodespacho.domain.GetClienteDbUseCase
import com.example.democarrodespacho.domain.GetProductosApiUseCase
import com.example.democarrodespacho.domain.SetClienteUseCase
import com.example.democarrodespacho.domain.SetDeletClienteUseCase
import com.example.democarrodespacho.domain.model.Cliente
import com.example.democarrodespacho.domain.model.Producto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val getClienteDbUseCase: GetClienteDbUseCase,
    private val setClienteUseCase: SetClienteUseCase,
    private val deletClienteUseCase: SetDeletClienteUseCase,
    ): ViewModel() {

    val clienteModel = MutableLiveData<Cliente?>()
    val isLoading = MutableLiveData<Boolean>()

    fun gelDbCliente(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val elCliente = getClienteDbUseCase()
            //Log.d("onCreate", "listProductos notNull: "+ listProductos.toString())
            clienteModel.postValue(elCliente)
            isLoading.postValue(false)
        }
    }

    fun agregarCliente(elCliente: ClienteEntity){
        viewModelScope.launch {
            isLoading.postValue(true)
            setClienteUseCase(elCliente)
            isLoading.postValue(false)
        }
    }

    fun borrarClienteDb(){
        viewModelScope.launch {
            deletClienteUseCase()
        }
    }


}