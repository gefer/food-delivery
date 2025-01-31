package br.com.ifood.features.products.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String? = null,
    val price: Double = 0.00,
    val imageUrl: String? = null
)
