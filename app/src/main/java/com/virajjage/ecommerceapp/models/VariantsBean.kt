package com.virajjage.ecommerceapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VariantsBean(
    var id : Int? = null,
    var color : String? = null,
    var size : Int? = null,
    var price : Double? = null
) : Parcelable