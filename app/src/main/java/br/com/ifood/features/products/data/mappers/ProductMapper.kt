package br.com.ifood.features.products.data.mappers

import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.domain.model.Product

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        details = this.description,
        cost = this.price,
        image = this.imageUrl
    )
}

fun Product.toDto(): ProductDto {
    return ProductDto(
        id = this.id,
        title = this.name,
        description = this.description,
        price = this.price,
        imageUrl = this.imageUrl
    )
}
