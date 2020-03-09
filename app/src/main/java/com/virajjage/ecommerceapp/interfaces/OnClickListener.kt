package com.virajjage.ecommerceapp.interfaces

import com.virajjage.ecommerceapp.models.ProductsBean

interface OnClickListener {
     fun onClick(productsBean: ProductsBean,categoryName : String)
}