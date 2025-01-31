package br.com.ifood.shared.navigation

sealed class Destinations(val route: String) {
    object Home : Destinations("home")
    object Products : Destinations("products")
    object Orders : Destinations("orders")
    object Delivery : Destinations("delivery")
    object Cart : Destinations("cart")
    object ProductDetail : Destinations("productDetail")
}
