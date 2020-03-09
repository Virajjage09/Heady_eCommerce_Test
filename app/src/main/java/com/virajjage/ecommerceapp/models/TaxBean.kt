package com.virajjage.ecommerceapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaxBean(
    @ColumnInfo(name = "tax_name")
    var name : String? = null,
    @ColumnInfo(name = "tax_value")
    var value : Double? = null
) : Parcelable