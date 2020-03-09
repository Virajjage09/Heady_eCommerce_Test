package com.virajjage.ecommerceapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.virajjage.ecommerceapp.db.DataTypeConverter

@Entity
data class CategoriesBean(
    @PrimaryKey
    var id: Int? = null,
    @ColumnInfo(name = "category_name")
    var name: String? = null,
    @TypeConverters(DataTypeConverter::class)
    var products: List<ProductsBean> = ArrayList()
)