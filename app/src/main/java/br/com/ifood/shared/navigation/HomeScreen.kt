package br.com.ifood.shared.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(Destinations.Products.route) }) {
            Text("Produtos")
        }
        Button(onClick = { navController.navigate(Destinations.Orders.route) }) {
            Text("Pedidos")
        }
        Button(onClick = { navController.navigate(Destinations.Delivery.route) }) {
            Text("Entrega")
        }
        Button(onClick = { navController.navigate(Destinations.Cart.route) }) {
            Text("Carrinho")
        }
    }
}