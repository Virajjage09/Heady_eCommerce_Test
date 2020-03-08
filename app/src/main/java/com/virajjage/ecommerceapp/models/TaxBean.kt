package com.virajjage.ecommerceapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaxBean(
    var name : String? = null,
    var value : Double? = null
) : Parcelable