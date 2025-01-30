package br.com.ifood.shared.navigation

sealed class Destinations(val route: String) {
    object Home : Destinations("home")
    object ProductDetail : Destinations("product_detail/{productId}") {
        fun createRoute(productId: String) = "product_detail/$productId"
    }
}
