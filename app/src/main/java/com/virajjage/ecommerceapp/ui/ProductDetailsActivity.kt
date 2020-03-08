package com.virajjage.ecommerceapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.virajjage.abl_test_viraj_jage.viewmodels.DashBoardActivityViewModel
import com.virajjage.ecommerceapp.R
import com.virajjage.ecommerceapp.models.ProductsBean
import com.virajjage.ecommerceapp.utils.ProjectUtils
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var categoryName: String
    private var productsBean: ProductsBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        initViews()
        initListener()
        initValues()
    }

    private fun initViews() {
        recColorList.apply {
            recColorList.layoutManager = LinearLayoutManager(this@ProductDetailsActivity,
                RecyclerView.HORIZONTAL,
                false)
        }
        recSizeList.apply {
            recSizeList.layoutManager = LinearLayoutManager(this@ProductDetailsActivity,
                RecyclerView.HORIZONTAL,
                false)
        }
    }

    private fun initListener() {

    }

    private fun initValues() {
        if (intent.extras != null) {
            productsBean = intent.extras!!.getParcelable<ProductsBean>("product_details")
            categoryName = intent.extras!!.getString("category_name").toString()
            parseVariantsData()
            setViewDetails()
        }

    }

    private fun setViewDetails() {
        imgProductView.setImageDrawable(
            ProjectUtils.getProductImage(
                this.applicationContext,
                categoryName
            )
        )
        tvProductName.text = productsBean?.name.toString()
        if (productsBean!!.variants.isNotEmpty()) {
            tvProductPrice.text =
                getString(R.string.rupee_sign) + " " + productsBean!!.variants[0].price
        }
    }

    private fun parseVariantsData(){
        var colorSet:MutableSet<String> = HashSet()
        var variantMap :  MutableMap<String,MutableList<Int>> = HashMap()
        for (variants in productsBean?.variants!!){
            colorSet.add(variants.color.toString())
        }
        Log.d("Color Array List","Color Array List$colorSet")
        for (color in colorSet) {
            var sizeArrayList:MutableList<Int> = ArrayList()
            for (variants in productsBean?.variants!!){
                if(color.equals(variants.color.toString())) {
                    sizeArrayList.add(variants.size?.toInt()!!)
                }
            }
            variantMap.put(color,sizeArrayList)
        }

        Log.d("Variant Map","Variant Map$variantMap")


    }

}
