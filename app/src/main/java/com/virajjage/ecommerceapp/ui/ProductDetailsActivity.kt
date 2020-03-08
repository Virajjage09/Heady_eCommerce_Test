package com.virajjage.ecommerceapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.virajjage.abl_test_viraj_jage.viewmodels.DashBoardActivityViewModel
import com.virajjage.ecommerceapp.R
import com.virajjage.ecommerceapp.adapters.ColorListAdapter
import com.virajjage.ecommerceapp.adapters.SizeListAdapter
import com.virajjage.ecommerceapp.interfaces.onColorPickListener
import com.virajjage.ecommerceapp.models.ProductsBean
import com.virajjage.ecommerceapp.utils.ProjectUtils
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity(),onColorPickListener {

    private lateinit var categoryName: String
    private var productsBean: ProductsBean? = null
    private var variantMap :  MutableMap<String,MutableList<Int>> = HashMap()
    private var colorSet:MutableSet<String> = HashSet()
    private lateinit var colorListAdapter : ColorListAdapter
    private lateinit var sizeListAdapter: SizeListAdapter

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

        colorListAdapter = ColorListAdapter(colorSet,this)
        recColorList.adapter = colorListAdapter

    }

    override fun onColorSelected(colorName: String) {
        tvColorName.text = colorName
        sizeListAdapter = SizeListAdapter(variantMap[colorName],this)
        recSizeList.adapter = sizeListAdapter
    }

}
