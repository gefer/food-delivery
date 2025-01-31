package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.mappers.toDomain
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.features.products.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val fetchProductsFromApiUseCase: FetchProductsFromApiUseCase,
    private val productRepository: ProductRepository
) {
    suspend fun execute(): Flow<List<Product>> {
        val productsFromDb = productRepository.getAllProductsFromDb().first()

        return if (productsFromDb.isNotEmpty()) {
            flowOf(productsFromDb.map { it.toDomain() })
        } else {
            fetchProductsFromApiUseCase.execute()
        }
    }
}







