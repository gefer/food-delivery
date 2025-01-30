package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend fun execute(): Flow<List<ProductEntity>> {
        return productRepository.getAllProductsFromDb()
    }
}


