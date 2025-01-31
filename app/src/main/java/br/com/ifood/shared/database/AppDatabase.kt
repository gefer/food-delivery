package br.com.ifood.shared.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.ifood.features.products.data.local.ProductDao
import br.com.ifood.features.products.data.model.ProductEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

@Singleton
class DatabaseHelper @Inject constructor(@ApplicationContext context: Context) {
    val db: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "food_delivery_db"
    ).build()
}
