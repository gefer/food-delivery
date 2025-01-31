package br.com.ifood.features.products.data.model

data class ProductDto(
    val id: String,
    val createdAt: String? = null,
    val title: String,
    val description: String? = null,
    val price: Double = 0.00,
    val imageUrl: String? = null
)
