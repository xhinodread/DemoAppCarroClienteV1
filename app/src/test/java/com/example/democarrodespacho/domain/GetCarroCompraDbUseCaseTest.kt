package com.example.democarrodespacho.domain

import com.example.democarrodespacho.data.CarroCompraRepository
import com.example.democarrodespacho.data.ResponseProductoRepository
import com.example.democarrodespacho.domain.model.CarroCompra
import com.example.democarrodespacho.domain.model.Pedidos
import com.example.democarrodespacho.domain.model.Producto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCarroCompraDbUseCaseTest{

    @RelaxedMockK
    private lateinit var carroCompraRepository: CarroCompraRepository

    lateinit var getCarroCompraDbUseCase: GetCarroCompraDbUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getCarroCompraDbUseCase = GetCarroCompraDbUseCase(carroCompraRepository)
    }

    @Test
    fun `when call carro de compra from DB then return list of product or emptyList`()= runBlocking{

        //Given
        val myList = listOf(
            CarroCompra(0, null, null),
            CarroCompra(0, null, null)
        )
        coEvery {
            carroCompraRepository.getCarroCompraFromDatabase()
        } returns myList

        //When
        val result =getCarroCompraDbUseCase()

        //Then
        coVerify(exactly = 1) {
            carroCompraRepository.getCarroCompraFromDatabase()
        }
        assert(result == myList)
    }
}