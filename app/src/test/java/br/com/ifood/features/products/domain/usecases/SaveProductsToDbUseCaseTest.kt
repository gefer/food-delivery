package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.features.products.data.mappers.toEntity
import br.com.ifood.features.products.data.model.ProductDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveProductsToDbUseCaseTest {

    private lateinit var saveProductsToDbUseCase: SaveProductsToDbUseCase
    private lateinit var productLocalDataSource: ProductLocalDataSource

    @Before
    fun setUp() {
        productLocalDataSource = mockk()
        saveProductsToDbUseCase = SaveProductsToDbUseCase(productLocalDataSource)
    }

    @Test
    fun `should save single pizza to DB`() = runTest {
        val pizzaDto = ProductDto(id = "1", title = "Margherita")

        // Simulando sucesso ao salvar a pizza no banco de dados
        coEvery { productLocalDataSource.insertProduct(any()) } returns Unit

        // Executando a função de salvar
        val result = saveProductsToDbUseCase.execute(pizzaDto)

        // Verificando se o método insertProduct foi chamado com a pizza correta
        coVerify { productLocalDataSource.insertProduct(pizzaDto.toEntity()) }

        // Verificando se o resultado é de sucesso
        assertTrue(result.isSuccess)
    }

    @Test
    fun `should save multiple pizzas to DB`() = runTest {
        val pizzasDto = listOf(
            ProductDto(id = "1", title = "Margherita"),
            ProductDto(id = "2", title = "Pepperoni"),
            ProductDto(id = "3", title = "Four Cheese")
        )

        // Simulando sucesso ao salvar as pizzas no banco de dados
        coEvery { productLocalDataSource.insertProduct(any()) } returns Unit

        // Executando a função de salvar múltiplas pizzas
        val result = saveProductsToDbUseCase.executeAllProductDtos(pizzasDto)

        // Verificando se o insertProduct foi chamado para cada pizza
        pizzasDto.forEach {
            coVerify { productLocalDataSource.insertProduct(it.toEntity()) }
        }

        // Verificando se o resultado é de sucesso
        assertTrue(result.isSuccess)
    }

    @Test
    fun `should handle error when saving single pizza to DB`() = runTest {
        val pizzaDto = ProductDto(id = "1", title = "Margherita")

        // Simulando erro ao salvar a pizza no banco de dados
        coEvery { productLocalDataSource.insertProduct(any()) } throws Exception("Database error")

        // Executando a função de salvar e verificando se ocorreu falha
        val result = saveProductsToDbUseCase.execute(pizzaDto)

        // Verificando se o método insertProduct foi chamado
        coVerify { productLocalDataSource.insertProduct(pizzaDto.toEntity()) }

        // Verificando se o resultado é um erro
        assertTrue(result.isFailure)
        assertEquals("Database error", result.exceptionOrNull()?.message)
    }

    @Test
    fun `should handle error when saving multiple pizzas to DB`() = runTest {
        val pizzasDto = listOf(
            ProductDto(id = "1", title = "Margherita"),
            ProductDto(id = "2", title = "Pepperoni"),
            ProductDto(id = "3", title = "Four Cheese")
        )

        // Simulando erro ao salvar uma das pizzas
        coEvery { productLocalDataSource.insertProduct(any()) } throws Exception("Database error")

        // Executando a função de salvar múltiplas pizzas e verificando falha
        val result = saveProductsToDbUseCase.executeAllProductDtos(pizzasDto)

        // Verificando se o insertProduct foi chamado para cada pizza
        pizzasDto.forEach {
            coVerify { productLocalDataSource.insertProduct(it.toEntity()) }
        }

        // Verificando se o resultado é de falha
        assertTrue(result.isFailure)
        assertEquals("Database error", result.exceptionOrNull()?.message)
    }
}
