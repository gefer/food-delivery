package br.com.ifood.features.products.presentation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductDetailScreen(productId: String) {
    Text(text = "Detalhes do Produto $productId")
}
