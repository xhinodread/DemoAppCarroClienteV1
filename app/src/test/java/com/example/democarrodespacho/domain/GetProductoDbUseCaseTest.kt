package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.domain.model.Producto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetProductoDbUseCaseTest {
    @RelaxedMockK
    private lateinit var productoRepository: ResponseProductoRepository

    lateinit var getProductoDbUseCase: GetProductoDbUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getProductoDbUseCase = GetProductoDbUseCase(productoRepository)
    }

    @Test
    fun `when the product is get then the DB return then product getting`()= runBlocking {
        //Given
        coEvery {
            productoRepository.getProductoFromDatabase("Leche")
        } returns Producto(0, null, 0, null)

        //When
        getProductoDbUseCase("Leche")

        //Then
        coVerify(exactly = 1) { productoRepository.getProductoFromDatabase("Leche") }

    }
}