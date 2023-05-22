package com.apptrick.collagemaker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.dataclasses.ImageData
import com.bumptech.glide.Glide

class DashBoardAdapter(val context: Context, private val listData: MutableList<ImageData>):
    RecyclerView.Adapter<DashBoardAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.recentImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.dashboard_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val value = listData[position]
        holder.img.clipToOutline = true
        Glide.with(context).load(value.uri).into(holder.img)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}