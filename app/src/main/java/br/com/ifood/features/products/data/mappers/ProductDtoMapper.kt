package br.com.ifood.features.products.data.mappers

import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.title,
        description = this.description,
        price = this.price,
        imageUrl = this.imageUrl
    )
}

fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.title,
        details = this.description,
        cost = this.price,
        image = this.imageUrl
    )
}