package com.virajjage.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.virajjage.ecommerceapp.R
import com.virajjage.ecommerceapp.interfaces.onColorPickListener


class SizeListAdapter(private val sizeArrayList:MutableList<Int>?,
                      private val onColorPickListener: onColorPickListener
) :
    RecyclerView.Adapter<SizeListAdapter.SizeListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeListHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout_size_list, parent, false)
        return SizeListHolder(itemView)
    }

    override fun getItemCount(): Int {
        return sizeArrayList?.size?.toInt()!!
    }

    override fun onBindViewHolder(holder: SizeListHolder, position: Int) {
        try {
            var size = sizeArrayList?.get(position)
            holder.tvSize.text = size.toString()
            holder.tvSize.setOnClickListener {
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    inner class SizeListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSize: TextView = itemView.findViewById(R.id.tvSize)
    }

}