package com.virajjage.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.virajjage.ecommerceapp.R
import com.virajjage.ecommerceapp.interfaces.OnColorPickListener
import com.virajjage.ecommerceapp.utils.ProjectUtils


class ColorListAdapter(private val colorSet: MutableSet<String>,
                       private val OnColorPickListener: OnColorPickListener
) :
    RecyclerView.Adapter<ColorListAdapter.ColorListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorListHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout_calor_list, parent, false)
        return ColorListHolder(itemView)
    }

    override fun getItemCount(): Int {
        return colorSet.size
    }

    override fun onBindViewHolder(holder: ColorListHolder, position: Int) {
        try {
            var color = colorSet.elementAt(position)
            holder.tvColor.setBackgroundColor(ProjectUtils.getProductColor(holder.itemView.context,color))
            holder.tvColor.setOnClickListener {
                OnColorPickListener.onColorSelected(color)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    inner class ColorListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvColor: TextView = itemView.findViewById(R.id.tvColor)
    }

}