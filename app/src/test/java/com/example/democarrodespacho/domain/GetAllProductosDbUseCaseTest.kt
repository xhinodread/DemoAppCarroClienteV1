package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.domain.model.Producto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllProductosDbUseCaseTest{
    @RelaxedMockK
    private lateinit var productoRepository: ResponseProductoRepository

    lateinit var getAllProductosDbUseCase: GetAllProductosDbUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getAllProductosDbUseCase = GetAllProductosDbUseCase(productoRepository)
    }

    @Test
    fun `when get all product from DB if then is not null return list of product else return empty list`()= runBlocking {

        //Giver
        val myList = listOf(Producto(0, null, 0, null), Producto(0, null, 0, null))
        coEvery {
            productoRepository.getAllProductosFromDatabase()
        } returns myList

        //When
        val productos = getAllProductosDbUseCase()

        //Then
        coVerify(exactly = 1) {
            productoRepository.getAllProductosFromDatabase()
        }
        assert(productos == myList)

    }

}