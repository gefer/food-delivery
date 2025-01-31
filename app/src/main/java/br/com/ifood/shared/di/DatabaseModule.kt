package br.com.ifood.shared.di

import android.app.Application
import androidx.room.Room
import br.com.ifood.features.products.data.local.ProductDao
import br.com.ifood.features.products.data.local.ProductLocalDataSource
import br.com.ifood.shared.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "food_delivery"
        ).build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideProductLocalDataSource(productDao: ProductDao): ProductLocalDataSource {
        return ProductLocalDataSource(productDao)
    }
}
