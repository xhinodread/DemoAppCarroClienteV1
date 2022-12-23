package com.example.democarrodespacho.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.democarrodespacho.data.database.entities.CarroCompraEntity
import com.example.democarrodespacho.data.database.entities.ClienteEntity
import com.example.democarrodespacho.data.database.entities.PedidosEntity
import com.example.democarrodespacho.data.database.entities.ProductoEntity
import com.example.democarrodespacho.domain.model.Cliente
import com.example.democarrodespacho.domain.model.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {

    /*** PRODUCTOS ***/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductos(productos: List<ProductoEntity>)

    @Query("SELECT * FROM productos")
    fun getProductos(): Flow<List<Producto>>

    @Query("SELECT * FROM productos")
    suspend fun getProductosDb(): List<ProductoEntity>

    @Query("SELECT * FROM productos where nombre = :nombre")
    suspend fun getProductosByNameDb(nombre: String): ProductoEntity

    @Query("SELECT * FROM productos where nombre LIKE '%'||:nombre||'%'")
    suspend fun getFilterProductosByNameDb(nombre: String): List<ProductoEntity>

    @Query("DELETE FROM productos")
    suspend fun delProductos()

    /*** CARRO DE COMPRAS (pedidos)***/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductoCarroCompra(producto: CarroCompraEntity)

    @Query("SELECT * FROM carro_compras")
    suspend fun getCarroCompraDb(): List<CarroCompraEntity>

    @Query("SELECT coalesce (sum(a_pagar), 0) as total " +
            "FROM(" +
            "    SELECT COUNT(nombre_producto) * B.precio as a_pagar " +
            "    FROM carro_compras as A " +
            "    INNER JOIN productos as B ON (A.nombre_producto = B.nombre ) " +
            "    GROUP BY A.nombre_producto )")
    suspend fun getTotalCarrocompraDb():Int

    @Query("SELECT COUNT(nombre_producto) as cantidad, nombre_producto, A.id, B.precio, COUNT(nombre_producto) * B.precio as a_pagar, B.foto " +
            "FROM carro_compras as A " +
            "INNER JOIN productos as B ON (A.nombre_producto = B.nombre ) " +
            "GROUP BY A.nombre_producto ORDER BY A.nombre_producto")
    suspend fun getPedidoCarroCompraDb(): List<PedidosEntity>

    @Query("SELECT COUNT(nombre_producto) as cantidad, nombre_producto, A.id, B.precio, COUNT(nombre_producto) * B.precio as a_pagar, B.foto " +
            "FROM carro_compras as A " +
            "INNER JOIN productos as B ON (A.nombre_producto = B.nombre ) " +
            "WHERE A.nombre_producto = :nombreProducto " +
            "GROUP BY A.nombre_producto ")
    suspend fun getProductoPedidoCarroCompraDb(nombreProducto: String): PedidosEntity

    @Query("DELETE FROM carro_compras WHERE id = (SELECT id FROM carro_compras WHERE nombre_producto = :nombreProducto LIMIT 1)")
    suspend fun deleteUnProductoCarroCompra(nombreProducto: String="")

    @Query("DELETE FROM carro_compras WHERE nombre_producto = :nombreProducto ")
    suspend fun deleteTodoProductoCarroCompra(nombreProducto: String="")

    /*** CLIENTE ***/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCliente(cliente: ClienteEntity)

    @Query("SELECT * FROM clientes LIMIT 1")
    suspend fun getClienteDb(): ClienteEntity

    @Query("DELETE FROM clientes")
    suspend fun deleteCliente()

}

/****+
 *
SELECT COUNT(nombre_producto) as cantidad, nombre_producto, id FROM carro_compras GROUP BY nombre_producto
 *
SELECT COUNT(nombre_producto) as cantidad, nombre_producto, A.id, B.precio, COUNT(nombre_producto) * B.precio as a_pagar
FROM carro_compras as A
INNER JOIN productos as B ON (A.nombre_producto = B.nombre )
GROUP BY A.nombre_producto
ORDER BY A.fecha
 *
 **/