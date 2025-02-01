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
        productDao = mockk(relaxed = true)
        productLocalDataSource = ProductLocalDataSource(productDao)
    }

    @Test
    fun `getAllProducts should return flow of products from DAO`() = runTest {
        val productList = listOf(
            ProductEntity(id = "1", name = "Pizza 1", cost = 10.0),
            ProductEntity(id = "2", name = "Pizza 1", cost = 20.0)
        )
        every { productDao.getAllProducts() } returns flowOf(productList)

        val resultFlow = productLocalDataSource.getAllProducts()

        resultFlow.test {
            assertEquals(productList, awaitItem()) // Verifica se a lista de produtos é emitida
            awaitComplete() // Verifica se o Flow foi concluído
        }
    }

    @Test
    fun `getAllProducts should return an empty list when no products are available`() = runTest {
        every { productDao.getAllProducts() } returns flowOf(emptyList())

        val resultFlow = productLocalDataSource.getAllProducts()

        resultFlow.test {
            assertEquals(emptyList<ProductEntity>(), awaitItem()) // Verifica se uma lista vazia é emitida
            awaitComplete() // Verifica se o Flow foi concluído
        }
    }

    @Test
    fun `getProductById should return flow of product from DAO`() = runTest {
        val productId = "1"
        val product = ProductEntity(id = productId, name = "Pizza 1", cost = 10.0)
        every { productDao.getProductById(productId) } returns flowOf(product)

        val resultFlow = productLocalDataSource.getProductById(productId)

        resultFlow.test {
            assertEquals(product, awaitItem()) // Verifica se o produto correto é emitido
            awaitComplete() // Verifica se o Flow foi concluído
        }
    }

    @Test
    fun `getProductById should return null when product is not found`() = runTest {
        val productId = "1"
        every { productDao.getProductById(productId) } returns flowOf(null)

        val resultFlow = productLocalDataSource.getProductById(productId)

        resultFlow.test {
            assertEquals(null, awaitItem()) // Verifica se null é emitido quando o produto não é encontrado
            awaitComplete() // Verifica se o Flow foi concluído
        }
    }

    @Test
    fun `insertProduct should call DAO insertProduct`() = runTest {
        val product = ProductEntity(id = "1", name = "Pizza 1", cost = 10.0)
        every { productDao.insertProduct(product) } returns Unit

        productLocalDataSource.insertProduct(product)

        verify(exactly = 1) { productDao.insertProduct(product) } // Verifica se o método foi chamado uma vez
    }

    @Test
    fun `insertAllProducts should call DAO insertAll`() = runTest {
        val productList = listOf(
            ProductEntity(id = "1", name = "Pizza 1", cost = 10.0),
            ProductEntity(id = "2", name = "Pizza 2", cost = 20.0)
        )
        every { productDao.insertAll(productList) } returns Unit

        productLocalDataSource.insertAllProducts(productList)

        verify(exactly = 1) { productDao.insertAll(productList) } // Verifica se o método foi chamado uma vez
    }

    @Test
    fun `insertAllProducts should call DAO insertAll with empty list`() = runTest {
        val emptyProductList = emptyList<ProductEntity>()
        every { productDao.insertAll(emptyProductList) } returns Unit

        productLocalDataSource.insertAllProducts(emptyProductList)

        verify(exactly = 1) { productDao.insertAll(emptyProductList) } // Verifica se o método foi chamado uma vez
    }
}
