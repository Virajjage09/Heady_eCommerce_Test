package com.virajjage.ecommerceapp.models

data class ProductsBean(
    var id : Int? = null,
    var name : String? = null,
    var date_added : String? = null,
    var tax : TaxBean? = null,
    var order_count : Long? = null,
    var view_count : Long? = null,
    var shares : Long? = null,
    var variants : ArrayList<VariantsBean> =  ArrayList()

)