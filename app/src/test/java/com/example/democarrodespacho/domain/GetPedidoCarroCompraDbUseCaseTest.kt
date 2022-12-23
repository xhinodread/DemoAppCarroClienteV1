package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.CarroCompraRepository
import com.example.democarrodespacho.data.model.ProductoModel
import com.example.democarrodespacho.domain.model.Pedidos
import com.example.democarrodespacho.domain.model.Producto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPedidoCarroCompraDbUseCaseTest{
    @RelaxedMockK
    private lateinit var carroCompraRepository: CarroCompraRepository

    lateinit var getPedidoCarroCompraDbUseCase: GetPedidoCarroCompraDbUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPedidoCarroCompraDbUseCase = GetPedidoCarroCompraDbUseCase(carroCompraRepository)
    }

    @Test
    fun `when get the list product from DB then return the list or empty`()= runBlocking {
        //Given
        val myList = listOf(
            Pedidos(0, null, null, null,null, null),
            Pedidos(0, null, null, null,null, null)
        )
        coEvery {
            carroCompraRepository.getPedidoCarroCompraFromDatabase()
        } returns myList

        //When
        getPedidoCarroCompraDbUseCase()

        //Then
        coVerify(exactly = 1) { carroCompraRepository.getPedidoCarroCompraFromDatabase() }

    }

}