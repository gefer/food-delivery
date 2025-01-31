package br.com.ifood.features.products.domain.usecases

import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.features.products.data.mappers.toEntity
import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.domain.model.Product
import kotlin.Result

class SaveProductsToDbUseCase(
    private val productLocalDataSource: ProductLocalDataSource
) {
    suspend fun execute(product: Product): Result<Unit> {
        return runCatching {
            productLocalDataSource.insertProduct(product.toEntity())
        }
    }

    suspend fun execute(productDto: ProductDto): Result<Unit> {
        return runCatching {
            productLocalDataSource.insertProduct(productDto.toEntity())
        }
    }

    suspend fun executeAllProducts(products: List<Product>): Result<Unit> {
        return runCatching {
            products.forEach { product ->
                productLocalDataSource.insertProduct(product.toEntity())
            }
        }
    }

    suspend fun executeAllProductDtos(products: List<ProductDto>): Result<Unit> {
        return runCatching {
            products.forEach { productDto ->
                productLocalDataSource.insertProduct(productDto.toEntity())
            }
        }
    }
}

