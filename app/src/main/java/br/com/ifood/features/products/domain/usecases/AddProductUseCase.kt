package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.mappers.toEntity
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.features.products.data.utils.NotificationService
import br.com.ifood.features.products.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddProductUseCase(
    private val productRepository: ProductRepository,
   // private val notificationService: NotificationService
) {
    suspend fun execute(product: Product): Flow<Result<ProductEntity>> {
        return flow {
            val productEntity = product.toEntity()
            val result = productRepository.addProduct(productEntity)
            if (result.isSuccess) {
               // notificationService.sendProductNotification(result.getOrNull()!!)
            }
            emit(result)
        }
    }
}


