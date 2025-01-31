package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.data.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveProductsToDbUseCaseTest {

    private lateinit var saveProductsToDbUseCase: SaveProductsToDbUseCase
    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        productRepository = mockk()
        saveProductsToDbUseCase = SaveProductsToDbUseCase(productRepository)
    }

    @Test
    fun `should save products in DB`() = runTest {
        val productsToSave = listOf(ProductDto(id = "1", title = "Product 1"))

        coEvery { productRepository.addProduct(any()) } returns Result.success(
            ProductEntity(
                id = "1",
                name = "Product 1"
            )
        )

        saveProductsToDbUseCase.execute(productsToSave)

        coVerify { productRepository.addProduct(any()) }
    }
}

