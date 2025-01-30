package br.com.ifood.features.products.data.repository

import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.data.remote.ProductRemoteDataSource
import br.com.ifood.shared.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val localDataSource: ProductLocalDataSource,
    private val remoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun getAllProductsFromDb(): Flow<List<ProductEntity>> {
        return localDataSource.getAllProducts()
    }

    override suspend fun getProductByIdFromDb(id: String): Flow<ProductEntity?> {
        return localDataSource.getProductById(id)
    }

    override suspend fun getAllProductsFromApi(): Flow<NetworkResult<List<ProductDto>>> {
        return remoteDataSource.fetchData()
    }

    override suspend fun addProduct(productEntity: ProductEntity): Result<ProductEntity> {
        return try {
            localDataSource.insertProduct(productEntity)
            Result.success(productEntity)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProductByIdFromApi(id: String): Flow<NetworkResult<ProductDto>> {
        TODO()
    }
}
