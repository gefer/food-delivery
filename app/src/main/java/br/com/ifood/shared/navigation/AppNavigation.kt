package br.com.ifood.shared.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.ifood.features.products.presentation.ui.ProductDetailScreen
import br.com.ifood.features.products.presentation.ui.ProductsScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Destinations.Home.route) {
        composable(Destinations.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Destinations.Products.route) {
            ProductsScreen(navController = navController)
        }

        composable(
            Destinations.ProductDetail.route + "/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            ProductDetailScreen(productId = productId)
        }
    }
}

