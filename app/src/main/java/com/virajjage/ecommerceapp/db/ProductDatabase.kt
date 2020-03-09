package com.virajjage.ecommerceapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.virajjage.ecommerceapp.models.CategoriesBean

@Database(entities = [CategoriesBean::class], version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter::class)
abstract class ProductDatabase : RoomDatabase() {


    abstract fun productDBAccess(): ProductDBAccess


    companion object {
        @Volatile
        private var instance: ProductDatabase? = null
        private val LOCK = Any()
        private var DB_NAME: String = "products.db"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            ProductDatabase::class.java, DB_NAME
        )
            .build()
    }
}