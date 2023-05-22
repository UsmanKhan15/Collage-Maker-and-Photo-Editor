package com.apptrick.collagemaker.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apptrick.collagemaker.utilityInterfaces.OnFolderItemClick

class GridsAdapter(val context: Context, val listData: ArrayList<String>, var onFolderItemClick: OnFolderItemClick):
    RecyclerView.Adapter<GridsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridsAdapter.MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GridsAdapter.MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}