package br.com.ifood.features.products.di

import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.features.products.data.remote.ProductRemoteDataSource
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.features.products.data.repository.ProductRepositoryImpl
import br.com.ifood.features.products.domain.usecases.AddProductUseCase
import br.com.ifood.features.products.domain.usecases.GetAllProductsUseCase
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
    fun provideAddProductUseCase(repository: ProductRepository): AddProductUseCase {
        return AddProductUseCase(repository)
    }

    @Provides
    fun provideGetProductsUseCase(repository: ProductRepository): GetAllProductsUseCase {
        return GetAllProductsUseCase(repository)
    }
}

