package com.virajjage.ecommerceapp.models

data class ResponseModel(
    val categories : ArrayList<CategoriesBean> = ArrayList(),
    val rankings : ArrayList<RankingBean> = ArrayList()
)