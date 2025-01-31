package br.com.ifood.features.products.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.ifood.features.products.presentation.viewmodel.ProductViewModel

@Composable
fun ProductsScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val productsState = viewModel.productsState.collectAsState()
    val products = productsState.value

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(products) { product ->
            ProductCard(
                product,
                onAddToCart = { })
        }
    }
}
