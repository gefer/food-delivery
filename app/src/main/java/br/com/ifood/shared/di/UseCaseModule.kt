package br.com.ifood.shared.di

import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.features.products.domain.usecases.FetchProductsFromApiUseCase
import br.com.ifood.features.products.domain.usecases.GetAllProductsUseCase
import br.com.ifood.features.products.domain.usecases.SaveProductsToDbUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllProductsUseCase(
        fetchProductsFromApiUseCase: FetchProductsFromApiUseCase,
        productRepository: ProductRepository
    ): GetAllProductsUseCase {
        return GetAllProductsUseCase(fetchProductsFromApiUseCase, productRepository)
    }

    @Provides
    fun provideSaveProductsToDbUseCase(productLocalDataSource: ProductLocalDataSource): SaveProductsToDbUseCase {
        return SaveProductsToDbUseCase(productLocalDataSource)
    }

    @Provides
    fun provideFetchProductsFromApiUseCase(
        productRepository: ProductRepository,
        saveProductsToDbUseCase: SaveProductsToDbUseCase
    ): FetchProductsFromApiUseCase {
        return FetchProductsFromApiUseCase(productRepository, saveProductsToDbUseCase)
    }
}
