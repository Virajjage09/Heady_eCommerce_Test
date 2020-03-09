package com.virajjage.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.virajjage.ecommerceapp.R
import com.virajjage.ecommerceapp.interfaces.OnClickListener
import com.virajjage.ecommerceapp.models.CategoriesBean

class CategoryListAdapter(private val categoryList: List<CategoriesBean>,
                          private val OnClickListener: OnClickListener) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryListHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout_category_list, parent, false)
        return CategoryListHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryListHolder, position: Int) {
        try {
            val categoriesBean = categoryList[position]
            holder.tvCategoryName.text = categoriesBean.name

            if(categoriesBean.products.isNotEmpty()) {
                holder.tvNotAvailable.visibility = View.GONE
                holder.recCategoryList.visibility = View.VISIBLE

                val childLayoutManager = LinearLayoutManager(
                    holder.recCategoryList.context,
                    RecyclerView.HORIZONTAL,
                    false
                )
                childLayoutManager.initialPrefetchItemCount = 4
                holder.recCategoryList.apply {
                    layoutManager = childLayoutManager
                    adapter =
                        ProductListAdapter(categoriesBean.products, categoriesBean.name.toString(),OnClickListener)
                    setRecycledViewPool(viewPool)
                }
            }else{
                holder.recCategoryList.visibility = View.GONE
                holder.tvNotAvailable.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    inner class CategoryListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
        var tvNotAvailable: TextView = itemView.findViewById(R.id.tvNotAvailable)
        var recCategoryList : RecyclerView = itemView.findViewById(R.id.recCategoryList)

    }

}