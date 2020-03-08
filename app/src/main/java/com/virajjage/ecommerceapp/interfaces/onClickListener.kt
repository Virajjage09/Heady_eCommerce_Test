package com.virajjage.ecommerceapp.interfaces

import com.virajjage.ecommerceapp.models.ProductsBean

interface onClickListener {
     fun onClick(productsBean: ProductsBean,categoryName : String)
}