package br.com.ifood.features.products.data.model

data class ProductDto(
    val id: String,
    val title: String,
    val description: String?,
    val price: Double,
    val imageUrl: String
)
