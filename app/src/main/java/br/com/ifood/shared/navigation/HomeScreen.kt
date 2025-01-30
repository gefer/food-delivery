package br.com.ifood.shared.navigation

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Button(onClick = {
        navController.navigate(Destinations.ProductDetail.createRoute("123"))
    }) {
        Text(text = "Ir para o Detalhe do Produto")
    }
}
