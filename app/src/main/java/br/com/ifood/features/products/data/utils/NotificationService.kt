package br.com.ifood.features.products.data.utils

import br.com.ifood.features.products.data.model.ProductEntity

class NotificationService {
    fun sendProductNotification(product: ProductEntity) {
        println("Notificando sobre o produto: ${product.name}")
    }
}
