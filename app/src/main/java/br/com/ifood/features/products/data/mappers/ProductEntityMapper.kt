package br.com.ifood.features.products.data.mappers

import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.name,
        description = this.details,
        price = this.cost,
        imageUrl = this.image
    )
}

fun ProductEntity.toDto(): ProductDto {
    return ProductDto(
        id = this.id,
        title = this.name,
        description = this.details,
        price = this.cost,
        imageUrl = this.image
    )
}