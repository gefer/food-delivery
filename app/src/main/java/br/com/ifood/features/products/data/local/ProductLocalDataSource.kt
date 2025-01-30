package br.com.ifood.features.products.data.local

import br.com.ifood.features.products.data.model.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }

    fun getProductById(id: String): Flow<ProductEntity?> {
        return productDao.getProductById(id)
    }

    fun insertProduct(productEntity: ProductEntity) {
        productDao.insertProduct(productEntity)
    }
}

