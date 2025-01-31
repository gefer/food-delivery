package br.com.ifood.features.products.presentation.viewmodel

import br.com.ifood.features.products.domain.usecases.FetchProductsFromApiUseCase
import br.com.ifood.features.products.domain.usecases.GetAllProductsUseCase
import br.com.ifood.features.products.domain.usecases.SaveProductsToDbUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.verify
import javax.inject.Inject

@HiltAndroidTest
class ProductViewModelTest {

    @Inject
    lateinit var getAllProductsUseCase: GetAllProductsUseCase

    @Inject
    lateinit var fetchProductsFromApiUseCase: FetchProductsFromApiUseCase

    @Inject
    lateinit var saveProductsToDbUseCase: SaveProductsToDbUseCase

    private lateinit var productViewModel: ProductViewModel

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()

        productViewModel = ProductViewModel(
            getAllProductsUseCase,
            fetchProductsFromApiUseCase,
            saveProductsToDbUseCase
        )
    }

    @Test
    fun `test fetch and save products from API`() = runTest {
        // Mocks e verificações podem ser feitas aqui
        productViewModel.fetchAndSaveProducts()

        // Verifica se o método execute() foi chamado no use case
        verify(fetchProductsFromApiUseCase).execute()
        verify(saveProductsToDbUseCase).execute(any())
    }

    @Test
    fun `test get all products from DB`() = runTest {
        productViewModel.getAllProducts()

        // Verifica se o método foi chamado corretamente
        verify(getAllProductsUseCase).execute()
    }
}
