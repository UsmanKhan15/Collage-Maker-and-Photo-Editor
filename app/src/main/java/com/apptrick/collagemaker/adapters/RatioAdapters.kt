package com.apptrick.collagemaker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.dataclasses.RatioData
import com.apptrick.collagemaker.utilityInterfaces.OnRatioItemClick

class RatioAdapters(val context: Context, private val listData: ArrayList<RatioData>,
                    private val onRatioItemClick: OnRatioItemClick):
    RecyclerView.Adapter<RatioAdapters.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.ratioImgView)
        val txt: TextView = itemView.findViewById(R.id.ratioTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.ratio_recview_design, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.img.setImageResource(listData[position].imgRatio)
        holder.txt.text = listData[position].txtRatio
        holder.itemView.setOnClickListener {
            onRatioItemClick.onRatioOptionClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}