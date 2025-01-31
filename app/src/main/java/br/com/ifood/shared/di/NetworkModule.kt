package br.com.ifood.shared.di

import br.com.ifood.features.products.data.remote.ProductRemoteDataSource
import br.com.ifood.features.products.data.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://679c0ee933d316846325d8aa.mockapi.io/api/ifood")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    fun provideRemoteDataSource(productService: ProductService): ProductRemoteDataSource {
        return ProductRemoteDataSource(productService)
    }
}
