package br.com.ifood.features.products.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val details: String? = null,
    val cost: Double = 0.00,
    val image: String? = null
)
