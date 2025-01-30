package br.com.ifood.features.products.data.remote

import br.com.ifood.features.products.data.model.ProductDto
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}