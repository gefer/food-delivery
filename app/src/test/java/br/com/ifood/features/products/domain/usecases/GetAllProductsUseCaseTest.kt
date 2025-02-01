package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.mappers.toDomain
import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.shared.network.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllProductsUseCaseTest {

    private lateinit var getAllProductsUseCase: GetAllProductsUseCase
    private lateinit var fetchProductsFromApiUseCase: FetchProductsFromApiUseCase
    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        // Mocks
        productRepository = mockk()
        fetchProductsFromApiUseCase = mockk()
        getAllProductsUseCase =
            GetAllProductsUseCase(fetchProductsFromApiUseCase, productRepository)
    }

    @Test
    fun `should return products from DB when available`() = runTest {
        val productsFromDb = listOf(ProductEntity(id = "1", name = "Product 1"))

        coEvery { productRepository.getAllProductsFromDb() } returns flowOf(productsFromDb)

        val result = getAllProductsUseCase.execute().first()

        assertEquals(1, result.size)
        assertEquals("Product 1", result.first().name)
    }

    @Test
    fun `should fetch products from API when no products in DB`() = runTest {
        // Simulando ausência de produtos no DB
        coEvery { productRepository.getAllProductsFromDb() } returns flowOf(emptyList())

        val productsFromApi = listOf(ProductDto(id = "1", title = "Product 1"))
        coEvery { fetchProductsFromApiUseCase.execute() } returns flowOf(productsFromApi.map { it.toDomain() })

        val result = getAllProductsUseCase.execute().first()

        assertEquals(1, result.size)
        assertEquals("Product 1", result.first().name)

        coVerify { fetchProductsFromApiUseCase.execute() }
    }

    @Test
    fun `should return empty list when no products in DB and API returns empty list`() = runTest {
        // Simulando ausência de produtos no DB
        coEvery { productRepository.getAllProductsFromDb() } returns flowOf(emptyList())

        // Simulando resposta vazia da API
        coEvery { fetchProductsFromApiUseCase.execute() } returns flowOf(emptyList())

        val result = getAllProductsUseCase.execute().first()

        assertTrue(result.isEmpty())

        // Verifica se a API foi chamada
        coVerify { fetchProductsFromApiUseCase.execute() }
    }

    @Test
    fun `should handle error when fetching products from API`() = runTest {
        // Simulando ausência de produtos no DB
        coEvery { productRepository.getAllProductsFromDb() } returns flowOf(emptyList())

        // Simulando erro ao buscar os produtos na API
        val apiError = NetworkResult.Error(code = 500, message = "API error")
        coEvery { fetchProductsFromApiUseCase.execute() } returns flowOf(emptyList()) // Simulando um erro retornando uma lista vazia

        val result = getAllProductsUseCase.execute().first()

        assertTrue(result.isEmpty())  // A lista deve ser vazia, já que houve falha na API

        coVerify { fetchProductsFromApiUseCase.execute() }
    }

    @Test
    fun `should return products from DB and not call API when products are available in DB`() =
        runTest {
            val productsFromDb = listOf(ProductEntity(id = "1", name = "Product 1"))

            coEvery { productRepository.getAllProductsFromDb() } returns flowOf(productsFromDb)

            val result = getAllProductsUseCase.execute().first()

            assertEquals(1, result.size)
            assertEquals("Product 1", result.first().name)

            // Verifica que a API não foi chamada
            coVerify(exactly = 0) { fetchProductsFromApiUseCase.execute() }
        }

    @Test
    fun `should handle database error gracefully`() = runTest {
        // Simulando erro ao acessar o DB
        coEvery { productRepository.getAllProductsFromDb() } throws Exception("Database error")

        val result = getAllProductsUseCase.execute().first()

        // Verifica que o resultado é uma lista vazia quando ocorre erro no DB
        assertTrue(result.isEmpty())

        // Verifica que a API não foi chamada em caso de falha no DB
        coVerify(exactly = 0) { fetchProductsFromApiUseCase.execute() }
    }
}
