package br.com.ifood.features.products.data.remote

import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.shared.network.NetworkDataSource
import br.com.ifood.shared.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRemoteDataSource(private val productService: ProductService) : NetworkDataSource<List<ProductDto>>() {
    override suspend fun fetchData(): Flow<NetworkResult<List<ProductDto>>> = flow {
        emit(fetchFromNetwork { productService.getProducts() })
    }
}
