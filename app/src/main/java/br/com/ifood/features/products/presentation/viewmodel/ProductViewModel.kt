package br.com.ifood.features.products.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ifood.features.products.data.model.ProductDto
import br.com.ifood.features.products.data.model.ProductEntity
import br.com.ifood.features.products.domain.model.Product
import br.com.ifood.features.products.domain.usecases.AddProductUseCase
import br.com.ifood.features.products.domain.usecases.GetAllProductsUseCase
import br.com.ifood.shared.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    private val _productsState = MutableStateFlow<List<ProductEntity>>(emptyList())
    val productsState: StateFlow<List<ProductEntity>> = _productsState

    private val _addProductResult = MutableStateFlow<Result<ProductEntity>?>(null)
    val addProductResult: StateFlow<Result<ProductEntity>?> = _addProductResult

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase.execute().collect { products ->
                _productsState.value = products
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            addProductUseCase.execute(product).collect { result ->
                _addProductResult.value = result
            }
        }
    }
}
