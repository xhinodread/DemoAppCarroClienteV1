package com.example.democarrodespacho.ui

import android.content.Context
import androidx.core.widget.addTextChangedListener
import com.example.democarrodespacho.data.database.CarroCompraDataBase
//import com.example.democarrodespacho.data.dto.CarroCompraDataBase
import com.example.democarrodespacho.domain.model.Producto
import com.example.democarrodespacho.databinding.ActivityMainBinding
import com.example.democarrodespacho.domain.PedidosAux
import com.example.democarrodespacho.ui.adapter.ProductoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainAux {

    private val context: Context? = null
    private val pedidosAux by lazy { PedidosAux() }
//    private val productoDataBase by lazy{
//        context?.let { CarroCompraDataBase.getDatabase(it.applicationContext).productosDao() }
//    }

//     fun initSearchButton(binding:ActivityMainBinding): Job {
//       // lifecycleScope.launch {
//           return CoroutineScope(Dispatchers.IO).launch {
//                val listadoProductoProvider: MutableList<Producto> =
//                    productoDataBase?.getProductosDb() as MutableList<Producto> // productoProvider.productoList
//                binding.txtViewSearch.addTextChangedListener { texto ->
//                    val filtroProductoProvider = listadoProductoProvider.filter { valor ->
//                        pedidosAux.filtroBuscador( valor, texto.toString() )
//                    }
//                    binding.rvProductos.adapter = ProductoAdapter(filtroProductoProvider) { productos ->
//                       // onClickListener(productos)
//                         productos
//                    }
//                }
//            }
//       // }
//    }

    fun filtroProductoProvider(lista: MutableList<Producto>, texto: String): List<Producto> {
        return lista.filter { valor ->
            pedidosAux.filtroBuscador( valor, texto)
        }
    }

}