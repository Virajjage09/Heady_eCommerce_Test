package com.virajjage.ecommerceapp.models

data class CategoriesBean(
    var id: Int? = null,
    var name: String? = null,
    var products: ArrayList<ProductsBean> = ArrayList()
)