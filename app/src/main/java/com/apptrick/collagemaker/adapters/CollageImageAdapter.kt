package com.apptrick.collagemaker.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.dataclasses.ImageData
import com.apptrick.collagemaker.utilityInterfaces.OnFolderItemClick
import com.bumptech.glide.Glide

class CollageImageAdapter(val context: Context, private val listData: MutableList<ImageData>,
                          private val onFolderItemClick: OnFolderItemClick):
    RecyclerView.Adapter<CollageImageAdapter.MyViewHolder>() {
    private var selectedPostion = 0
    companion object{
        private var listPosition: ArrayList<Uri> = ArrayList()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.collageImg)
        val selected: ImageView = itemView.findViewById(R.id.selectedImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.collage_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val value = listData[position]
        Glide.with(context).load(value.uri).into(holder.img)
        holder.itemView.setOnClickListener {
            if(listPosition.contains(value.uri))
            {
                listPosition.remove(value.uri)
                holder.selected.visibility = View.INVISIBLE
            }
            else
                listPosition.add(value.uri!!)
            if(selectedPostion==RecyclerView.NO_POSITION)
                return@setOnClickListener
            notifyItemChanged(selectedPostion)
            selectedPostion = position
            notifyItemChanged(selectedPostion)
            onFolderItemClick.onSelect(position, value.uri!!.normalizeScheme())
        }
        if(listPosition.contains(value.uri))
        {
            holder.selected.visibility = View.VISIBLE
        }
        else
            holder.selected.visibility = View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}