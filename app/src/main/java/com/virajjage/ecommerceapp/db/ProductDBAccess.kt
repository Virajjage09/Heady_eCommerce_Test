package com.virajjage.ecommerceapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.virajjage.ecommerceapp.models.CategoriesBean

@Dao
interface ProductDBAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categoriesBean: CategoriesBean)

    @Query("Select * from CategoriesBean")
    fun getAllCategory(): List<CategoriesBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(productList: List<CategoriesBean>)

    @Query("DELETE FROM CategoriesBean")
    fun deleteAllCategories()

}