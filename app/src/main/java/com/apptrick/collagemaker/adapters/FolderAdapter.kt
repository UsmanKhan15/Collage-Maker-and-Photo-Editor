package com.apptrick.collagemaker.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.utilityInterfaces.OnFolderItemClick

class FolderAdapter(val context: Context, private val listData: ArrayList<String>, var onFolderItemClick: OnFolderItemClick):
    RecyclerView.Adapter<FolderAdapter.MyViewHolder>() {
    private var selectedPostion = 0
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
       val folderTxtView: TextView = itemView.findViewById(R.id.txtFolder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.folder_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val value = listData[position]
        holder.folderTxtView.text = value
        holder.itemView.setOnClickListener {
            if(selectedPostion==RecyclerView.NO_POSITION)
                return@setOnClickListener
            notifyItemChanged(selectedPostion)
            selectedPostion = position
            notifyItemChanged(selectedPostion)
            onFolderItemClick.onClick(position, value)
        }
        if(selectedPostion == position)
        {
            holder.folderTxtView.setBackgroundResource(R.drawable.bg_selected_folder)
        }
        else
            holder.folderTxtView.setBackgroundResource(R.drawable.txt_bg)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}