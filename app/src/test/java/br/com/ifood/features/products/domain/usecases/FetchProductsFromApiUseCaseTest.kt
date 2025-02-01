package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.shared.network.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FetchProductsFromApiUseCaseTest {

    private lateinit var fetchProductsFromApiUseCase: FetchProductsFromApiUseCase
    private lateinit var productRepository: ProductRepository
    private lateinit var saveProductsToDbUseCase: SaveProductsToDbUseCase

    @Before
    fun setUp() {
        productRepository = mockk()
        saveProductsToDbUseCase = mockk()
        fetchProductsFromApiUseCase =
            FetchProductsFromApiUseCase(productRepository, saveProductsToDbUseCase)
    }

    @Test
    fun `should fetch products from API, map to domain and save them in DB`() = runTest {
        val productsFromApi = listOf(ProductDto(id = "1", title = "Pizza de 4 Queijos"))
        val networkResult = NetworkResult.Success(productsFromApi)

        // Alterando para coEvery para simular corretamente um retorno Unit
        coEvery { productRepository.getAllProductsFromApi() } returns flowOf(networkResult)
        coEvery { saveProductsToDbUseCase.executeAllProductDtos(productsFromApi) } returns Result.success(Unit)

        val result = fetchProductsFromApiUseCase.execute().first()

        assertEquals(1, result.size)
        assertEquals("Pizza de 4 Queijos", result.first().name)
        coVerify { saveProductsToDbUseCase.executeAllProductDtos(productsFromApi) }
    }

    @Test
    fun `should return empty list when API request fails with error`() = runTest {
        val networkResult = NetworkResult.Error(code = 500, message = "API error")

        coEvery { productRepository.getAllProductsFromApi() } returns flowOf(networkResult)

        val result = fetchProductsFromApiUseCase.execute().first()

        assertEquals(0, result.size)
        coVerify(exactly = 0) { saveProductsToDbUseCase.executeAllProductDtos(any()) }
    }

    @Test
    fun `should return empty list when API returns empty list of products`() = runTest {
        val networkResult = NetworkResult.Success(emptyList<ProductDto>())

        coEvery { productRepository.getAllProductsFromApi() } returns flowOf(networkResult)

        val result = fetchProductsFromApiUseCase.execute().first()

        assertEquals(0, result.size)
        coVerify { saveProductsToDbUseCase.executeAllProductDtos(emptyList()) }
    }

    @Test
    fun `should handle exception during API call and return empty list`() = runTest {
        coEvery { productRepository.getAllProductsFromApi() } throws Exception("Network error")

        val result = fetchProductsFromApiUseCase.execute().first()

        assertEquals(0, result.size)
        coVerify(exactly = 0) { saveProductsToDbUseCase.executeAllProductDtos(any()) }
    }

    @Test
    fun `should map DTOs to domain objects correctly`() = runTest {
        val productsFromApi = listOf(ProductDto(id = "1", title = "Pizza de 4 Queijos"))
        val networkResult = NetworkResult.Success(productsFromApi)

        coEvery { productRepository.getAllProductsFromApi() } returns flowOf(networkResult)

        val result = fetchProductsFromApiUseCase.execute().first()

        assertEquals(1, result.size)
        val domainProduct = result.first()
        assertEquals("Pizza de 4 Queijos", domainProduct.name)
    }
}
