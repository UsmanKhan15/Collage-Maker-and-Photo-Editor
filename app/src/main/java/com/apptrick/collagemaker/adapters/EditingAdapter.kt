package com.apptrick.collagemaker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.dataclasses.EditingOptions
import com.apptrick.collagemaker.utilityInterfaces.OnFrameItemClick
import com.bumptech.glide.Glide

class EditingAdapter(val context: Context, private val listData: ArrayList<EditingOptions>,
                     val onFrameItemClick: OnFrameItemClick):
    RecyclerView.Adapter<EditingAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgEdtOptions)
        val txt: TextView = itemView.findViewById(R.id.txtEdtOptions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.editing_options_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(listData[position].imgEdit).into(holder.img)
        holder.txt.text = listData[position].txtEdit.toString()
        holder.itemView.setOnClickListener {
            onFrameItemClick.onEditOptionClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}