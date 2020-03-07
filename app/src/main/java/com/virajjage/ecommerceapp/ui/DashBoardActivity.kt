package com.virajjage.ecommerceapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.virajjage.abl_test_viraj_jage.viewmodels.DashBoardActivityViewModel
import com.virajjage.ecommerceapp.R
import com.virajjage.ecommerceapp.adapters.CategoryListAdapter
import com.virajjage.ecommerceapp.models.CategoriesBean
import com.virajjage.ecommerceapp.models.ResponseModel
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashBoardActivity : AppCompatActivity() {

    private lateinit var dashBoardActivityViewModel: DashBoardActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initViews()
        initListener()
        initValues()
    }

    private fun initViews() {
        recCategoryList.apply {
            recCategoryList.layoutManager = LinearLayoutManager(this@DashBoardActivity)
        }

    }

    private fun initListener() {

    }

    private fun initValues() {
        dashBoardActivityViewModel =
            ViewModelProvider(this).get(DashBoardActivityViewModel::class.java)

        dashBoardActivityViewModel.callProductListAPI().observe(this, productListObserver)

    }

    private val productListObserver = Observer<ResponseModel> { response ->
        if (response != null) {
            val strResponse = Gson().toJson(response)
            Log.d("TAG", "Success Response $strResponse")
            setAdapter(response.categories)
        }
    }

    private fun setAdapter(categoryList: List<CategoriesBean>){
        if(categoryList.isNotEmpty()){
            var categoryListAdapter = CategoryListAdapter(categoryList)
            recCategoryList.adapter = categoryListAdapter
        }
    }
}
