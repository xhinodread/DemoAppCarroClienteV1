package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.data.database.entities.ProductoEntity
import com.example.democarrodespacho.data.model.ProductoModel
import com.example.democarrodespacho.data.model.ResponseProducto
import com.example.democarrodespacho.domain.model.Producto
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.Result
import retrofit2.Response

class GetProductosApiUseCaseTest{

    @RelaxedMockK
    private lateinit var productoRepository: ResponseProductoRepository

    lateinit var getProductosApiUseCase: GetProductosApiUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getProductosApiUseCase = GetProductosApiUseCase(productoRepository)
    }

    @Test
    fun `when the api doesnt return anything then return emptylist`()= runBlocking {
        //Given
        val myList = listOf(ProductoModel(0, "null", 0, "null"), ProductoModel(0, "null", 0, "null"))
        val response = ResponseProducto("success", myList )
        coEvery { productoRepository.getAllProductosFromApi() } returns response

        //When
        getProductosApiUseCase()

        //Then
        coVerify(exactly = 1) { emptyList<ResponseProducto>() }
    }
}

private infix fun <T, B> MockKStubScope<T, B>.returns(response: ResponseProducto): ResponseProducto {
    val myList = listOf(ProductoModel(0, "null", 0, "null"), ProductoModel(0, "null", 0, "null"))
    val response = ResponseProducto("success", myList )
    return response
}
