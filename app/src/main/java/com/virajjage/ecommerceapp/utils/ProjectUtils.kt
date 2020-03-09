package com.virajjage.ecommerceapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.virajjage.ecommerceapp.R

object ProjectUtils {

    fun getProductImage(context:Context,productName : String) : Drawable?{
        return when(productName.trim()){
            "Casuals" -> ContextCompat.getDrawable(context,R.drawable.ic_casual_shoes)
            "Jeans" -> ContextCompat.getDrawable(context,R.drawable.ic_men_jeans)
            "T-Shirts" -> ContextCompat.getDrawable(context,R.drawable.ic_men_tshirt)
            "Tracks & Trousers" -> ContextCompat.getDrawable(context,R.drawable.ic_tracks_trousers)
            "Formals" -> ContextCompat.getDrawable(context,R.drawable.ic_formal_shoes)
            "Shirts" -> ContextCompat.getDrawable(context,R.drawable.ic_men_shirt)
            "Apple" -> ContextCompat.getDrawable(context,R.drawable.ic_apple_mobiles)
            "Samsung" -> ContextCompat.getDrawable(context,R.drawable.ic_samsung_phones)
            "Dell" -> ContextCompat.getDrawable(context,R.drawable.ic_dell_laptop)
            "Toshiba" -> ContextCompat.getDrawable(context,R.drawable.ic_toshiba_laptop)
            else -> ContextCompat.getDrawable(context,R.drawable.ic_image_not_available)
        }
    }

    fun getProductColor(context:Context,colorName : String) : Int{
        return when(colorName.trim()){
            "White" -> ContextCompat.getColor(context,R.color.colorWhite)
            "Red" -> ContextCompat.getColor(context,R.color.colorRed)
            "Black" -> ContextCompat.getColor(context,R.color.colorBlack)
            "Blue" -> ContextCompat.getColor(context,R.color.colorBlue)
            "Brown" -> ContextCompat.getColor(context,R.color.colorBrown)
            else -> ContextCompat.getColor(context,R.color.colorPrimary)
        }
    }
}