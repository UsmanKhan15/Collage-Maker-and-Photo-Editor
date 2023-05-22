package com.apptrick.collagemaker.editingCollageUtilityClasses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.adapters.RatioAdapters
import com.apptrick.collagemaker.databinding.FragmentSelectCollageBinding
import com.apptrick.collagemaker.dataclasses.RatioData
import com.apptrick.collagemaker.utilityInterfaces.OnRatioItemClick

class RatioOptions: OnRatioItemClick {

    private lateinit var ratioAdapters: RatioAdapters
    private var listRatio: ArrayList<RatioData> = ArrayList()

    fun ratioOptions(binding: FragmentSelectCollageBinding, requireContext: Context) {
        binding.toolbarSelectGrid.title = "Ratio"
        binding.parentGrids.removeAllViews()
        val view: View = LayoutInflater.from(requireContext).inflate(
            R.layout.ratio_dialog, binding.root,
            false)
        val ratioRecView = view.findViewById<RecyclerView>(R.id.ratioRecView)
        listRatio.clear()
        listRatio.add(RatioData(R.drawable.ic_baseline_crop_3_2_24, "3:2"))
        listRatio.add(RatioData(R.drawable.ic_baseline_crop_din_24, "2:3"))
        listRatio.add(RatioData(R.drawable.ic_baseline_crop_16_9_24, "3:4"))
        ratioAdapters = RatioAdapters(requireContext, listRatio, this@RatioOptions)
        ratioRecView.adapter = ratioAdapters
        ratioRecView.layoutManager = LinearLayoutManager(requireContext,
            LinearLayoutManager.HORIZONTAL, false)
        binding.parentGrids.addView(view)
    }

    override fun onRatioOptionClick(position: Int) {

    }
}