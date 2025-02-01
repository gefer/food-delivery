package br.com.ifood.features.products.data

import app.cash.turbine.test
import br.com.ifood.features.products.data.local.ProductDao
import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.features.products.data.model.ProductEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductLocalDataSourceTest {

    private lateinit var productDao: ProductDao
    private lateinit var productLocalDataSource: ProductLocalDataSource

    @Before
    fun setUp() {
        productDao = mockk()
        productLocalDataSource = ProductLocalDataSource(productDao)
    }

    @Test
    fun `getAllProducts should return flow of products from DAO`() = runTest {
        val productList = listOf(
            ProductEntity(id = "1", name = "Product 1", cost = 10.0),
            ProductEntity(id = "2", name = "Product 2", cost = 20.0)
        )
        every { productDao.getAllProducts() } returns flowOf(productList)

        val resultFlow = productLocalDataSource.getAllProducts()

        resultFlow.test {
            assertEquals(productList, awaitItem()) // Verifica se a lista de produtos é emitida
            awaitComplete() // Verifica se o Flow foi concluído
        }
    }

    @Test
    fun `getProductById should return flow of product from DAO`() = runTest {
        // Arrange
        val productId = "1"
        val product = ProductEntity(id = productId, name = "Product 1", cost = 10.0)
        every { productDao.getProductById(productId) } returns flowOf(product)

        // Act
        val resultFlow = productLocalDataSource.getProductById(productId)

        // Assert
        resultFlow.test {
            assertEquals(product, awaitItem()) // Verifica se o produto correto é emitido
            awaitComplete() // Verifica se o Flow foi concluído
        }
    }

    @Test
    fun `insertProduct should call DAO insertProduct`() = runTest {
        // Arrange
        val product = ProductEntity(id = "1", name = "Product 1", cost = 10.0)
        every { productDao.insertProduct(product) } returns Unit

        // Act
        productLocalDataSource.insertProduct(product)

        // Assert
        verify(exactly = 1) { productDao.insertProduct(product) } // Verifica se o método foi chamado
    }

    @Test
    fun `insertAllProducts should call DAO insertAll`() = runTest {
        // Arrange
        val productList = listOf(
            ProductEntity(id = "1", name = "Product 1", cost = 10.0),
            ProductEntity(id = "2", name = "Product 2", cost = 20.0)
        )
        every { productDao.insertAll(productList) } returns Unit

        // Act
        productLocalDataSource.insertAllProducts(productList)

        // Assert
        verify(exactly = 1) { productDao.insertAll(productList) } // Verifica se o método foi chamado
    }
}