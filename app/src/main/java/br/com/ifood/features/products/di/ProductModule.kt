package br.com.ifood.features.products.di

import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.features.products.data.remote.ProductRemoteDataSource
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.features.products.data.repository.ProductRepositoryImpl
import br.com.ifood.features.products.domain.usecases.FetchProductsFromApiUseCase
import br.com.ifood.features.products.domain.usecases.GetAllProductsUseCase
import br.com.ifood.features.products.domain.usecases.SaveProductsToDbUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ProductModule {

    @Provides
    fun provideProductRepositoryImpl(
        localDataSource: ProductLocalDataSource,
        remoteDataSource: ProductRemoteDataSource
    ): ProductRepository {
        return ProductRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetAllProductsUseCase(
        fetchProductsFromApiUseCase: FetchProductsFromApiUseCase,
        repository: ProductRepository
    ): GetAllProductsUseCase {
        return GetAllProductsUseCase(fetchProductsFromApiUseCase, repository)
    }

    @Provides
    fun provideFetchProductsFromApiUseCase(
        repository: ProductRepository,
        saveProductsToDbUseCase: SaveProductsToDbUseCase
    ): FetchProductsFromApiUseCase {
        return FetchProductsFromApiUseCase(repository, saveProductsToDbUseCase)
    }

    @Provides
    fun provideSaveProductsToDbUseCase(productLocalDataSource: ProductLocalDataSource): SaveProductsToDbUseCase {
        return SaveProductsToDbUseCase(productLocalDataSource)
    }
}


