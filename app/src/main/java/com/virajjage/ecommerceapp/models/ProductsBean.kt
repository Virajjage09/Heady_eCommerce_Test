package com.virajjage.ecommerceapp.models

import android.os.Parcelable
import androidx.room.*
import com.virajjage.ecommerceapp.db.DataTypeConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ProductsBean(
    @PrimaryKey
    var id : Int? = null,
    @ColumnInfo(name = "product_name")
    var name : String? = null,
    @ColumnInfo(name = "product_add_date")
    var date_added : String? = null,
    @Embedded
    var tax : TaxBean? = null,
    @ColumnInfo(name = "order_count")
    var order_count : Long? = null,
    @ColumnInfo(name = "view_count")
    var view_count : Long? = null,
    @ColumnInfo(name = "shares_count")
    var shares : Long? = null,
    @TypeConverters(DataTypeConverter::class)
    var variants : List<VariantsBean> =  ArrayList()

) : Parcelable