package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.mappers.toDomain
import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.data.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
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
}
