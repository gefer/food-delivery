package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.mappers.toDomain
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.features.products.domain.model.Product
import br.com.ifood.shared.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchProductsFromApiUseCase(
    private val productRepository: ProductRepository,
    private val saveProductsToDbUseCase: SaveProductsToDbUseCase
) {
    suspend fun execute(): Flow<List<Product>> {
        return productRepository.getAllProductsFromApi().map { networkResult ->
            when (networkResult) {
                is NetworkResult.Success -> {
                    saveProductsToDbUseCase.executeAllProductDtos(networkResult.data)
                    networkResult.data.map { it.toDomain() }
                }

                else -> {
                    emptyList()
                }
            }
        }
    }
}
