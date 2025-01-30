package br.com.ifood.features.products.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val details: String?,
    val cost: Double,
    val image: String
)

