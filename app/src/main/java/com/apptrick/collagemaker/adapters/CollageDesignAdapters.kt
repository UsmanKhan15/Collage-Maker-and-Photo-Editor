package com.apptrick.collagemaker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.utilityInterfaces.OnFrameItemClick

class CollageDesignAdapters(val context: Context, private val listData: ArrayList<Int>,
                            private val onFrameItemClick: OnFrameItemClick):
    RecyclerView.Adapter<CollageDesignAdapters.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.recentImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.dashboard_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.img.setImageResource(listData[position])
        holder.itemView.setOnClickListener {
            onFrameItemClick.onFrameClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}