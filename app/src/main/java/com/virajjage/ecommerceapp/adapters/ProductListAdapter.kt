package com.virajjage.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.virajjage.ecommerceapp.R
import com.virajjage.ecommerceapp.interfaces.OnClickListener
import com.virajjage.ecommerceapp.models.ProductsBean
import com.virajjage.ecommerceapp.utils.ProjectUtils


class ProductListAdapter(private val productList: List<ProductsBean>,
                         private val categoryName : String,
                         private val OnClickListener: OnClickListener
) :
    RecyclerView.Adapter<ProductListAdapter.ProductListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_child_layout_products, parent, false)
        return ProductListHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductListHolder, position: Int) {
        try {
            val resource = holder.itemView.resources
            val productsBean = productList[position]
            holder.tvProductName.text = productsBean.name
            if(productsBean.variants.isNotEmpty()) {
                holder.tvProductPrice.text =
                    resource.getString(R.string.rupee_sign) + " " + productsBean.variants[0].price
            }
            holder.imgChildView.setImageDrawable(ProjectUtils.getProductImage(holder.itemView.context,categoryName))

            holder.cardProduct.setOnClickListener {
                OnClickListener.onClick(productsBean,categoryName)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    inner class ProductListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        var tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        var imgChildView : ImageView = itemView.findViewById(R.id.imgChildView)
        var cardProduct : CardView = itemView.findViewById(R.id.cardProduct)
    }

}