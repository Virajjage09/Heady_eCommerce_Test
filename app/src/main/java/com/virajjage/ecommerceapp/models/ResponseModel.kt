package com.virajjage.ecommerceapp.models

data class ResponseModel(
    val categories : List<CategoriesBean> = ArrayList(),
    val rankings : List<RankingBean> = ArrayList()
)