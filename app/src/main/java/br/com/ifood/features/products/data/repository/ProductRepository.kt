package br.com.ifood.features.products.data.repository

import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.shared.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getAllProductsFromDb(): Flow<List<ProductEntity>>

    suspend fun getProductByIdFromDb(id: String): Flow<ProductEntity?>

    suspend fun getAllProductsFromApi(): Flow<NetworkResult<List<ProductDto>>>

    suspend fun getProductByIdFromApi(id: String): Flow<NetworkResult<ProductDto>>

    suspend fun addProduct(productEntity: ProductEntity): Result<ProductEntity>
}







