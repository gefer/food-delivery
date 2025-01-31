package br.com.ifood.shared.di

import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.features.products.data.remote.ProductRemoteDataSource
import br.com.ifood.features.products.data.repository.ProductRepository
import br.com.ifood.features.products.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideProductRepository(
        localDataSource: ProductLocalDataSource,
        remoteDataSource: ProductRemoteDataSource
    ): ProductRepository {
        return ProductRepositoryImpl(localDataSource, remoteDataSource)
    }
}
