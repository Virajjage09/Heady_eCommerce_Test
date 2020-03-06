package com.virajjage.ecommerceapp.models

data class RankingBean(
    var ranking: String? = null,
    var products: ArrayList<ProductsBean> = ArrayList()
)