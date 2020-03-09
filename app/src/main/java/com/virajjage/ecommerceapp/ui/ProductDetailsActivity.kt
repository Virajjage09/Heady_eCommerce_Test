package com.virajjage.ecommerceapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.virajjage.ecommerceapp.R
import com.virajjage.ecommerceapp.adapters.ColorListAdapter
import com.virajjage.ecommerceapp.adapters.SizeListAdapter
import com.virajjage.ecommerceapp.interfaces.OnColorPickListener
import com.virajjage.ecommerceapp.models.ProductsBean
import com.virajjage.ecommerceapp.utils.ProjectUtils
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity(), OnColorPickListener {

    private lateinit var categoryName: String
    private var productsBean: ProductsBean? = null
    private var variantMap: MutableMap<String, MutableList<Int>> = HashMap()
    private var colorSet: MutableSet<String> = HashSet()
    private lateinit var colorListAdapter: ColorListAdapter
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
            recColorList.layoutManager = LinearLayoutManager(
                this@ProductDetailsActivity,
                RecyclerView.HORIZONTAL,
                false
            )
        }
        recSizeList.apply {
            recSizeList.layoutManager = LinearLayoutManager(
                this@ProductDetailsActivity,
                RecyclerView.HORIZONTAL,
                false
            )
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

    private fun parseVariantsData() {
        for (variants in productsBean?.variants!!) {
            colorSet.add(variants.color.toString())
        }
        Log.d("Color Array List", "Color Array List$colorSet")
        for (color in colorSet) {
            var sizeArrayList: MutableList<Int> = ArrayList()
            for (variants in productsBean?.variants!!) {
                if (color.equals(variants.color.toString())) {
                    variants.size?.toInt()?.let { sizeArrayList.add(it) }
                }
            }
            variantMap[color] = sizeArrayList
        }

        Log.d("Variant Map", "Variant Map$variantMap")

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

        setColorListAdapter()
        if (colorSet.isNotEmpty()) {
            tvColorName.text = colorSet.elementAt(0)
            setSizeListAdapter(colorSet.elementAt(0))
        }
    }


    private fun setColorListAdapter() {
        if (colorSet.isNotEmpty()) {
            colorListAdapter = ColorListAdapter(colorSet, this)
            recColorList.adapter = colorListAdapter
        }
    }

    private fun setSizeListAdapter(colorName: String) {
        if (variantMap[colorName]?.isNotEmpty() as Boolean) {
            sizeListAdapter = SizeListAdapter(variantMap[colorName], this)
            recSizeList.adapter = sizeListAdapter
        } else {
            tvSizeOption.visibility = View.GONE
            line3.visibility = View.GONE
        }
    }

    private fun updateProductPrice(colorName: String, size: Int) {
        for (variants in productsBean?.variants!!) {
            if (variantMap[colorName]?.isNotEmpty() as Boolean) {
                if (variants.color.equals(colorName) && variants.size == size) {
                    tvProductPrice.text =
                        getString(R.string.rupee_sign) + " " + variants.price.toString()
                }
            } else {
                if (variants.color.equals(colorName)) {
                    tvProductPrice.text =
                        getString(R.string.rupee_sign) + " " + variants.price.toString()
                }
            }
        }

    }

    override fun onColorSelected(colorName: String) {
        tvColorName.text = colorName
        setSizeListAdapter(colorName)
        if(variantMap[colorName]?.isNotEmpty() as Boolean){
            variantMap[colorName]?.get(0)?.let { updateProductPrice(colorName, it) }
        }else {
            updateProductPrice(colorName, 0)
        }
    }

    override fun onSizeSelected(size: Int) {
        updateProductPrice(tvColorName.text.toString(), size)
    }

}
