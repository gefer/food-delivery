package br.com.ifood.features.products.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ifood.features.products.domain.model.Product
import br.com.ifood.features.products.domain.usecases.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _productsState = MutableStateFlow<List<Product>>(emptyList())
    val productsState: StateFlow<List<Product>> = _productsState

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase.execute()
                .collect { productEntities ->
                    _productsState.value = productEntities
                }
        }
    }
}
