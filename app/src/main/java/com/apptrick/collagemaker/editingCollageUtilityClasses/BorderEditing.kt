package com.apptrick.collagemaker.editingCollageUtilityClasses

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.apptrick.collagemaker.databinding.BordersDialogBinding
import com.apptrick.collagemaker.databinding.FragmentSelectCollageBinding
import com.apptrick.collagemaker.viewModel.ImageViewModel

class BorderEditing(requireActivity: ViewModelStoreOwner) {
    lateinit var bindingBorder: BordersDialogBinding
    val viewModel: ImageViewModel by lazy{
        ViewModelProvider(requireActivity)[ImageViewModel::class.java]
    }
    @SuppressLint("MissingInflatedId")
    fun borderOptions(binding: FragmentSelectCollageBinding, requireContext: Context) {
        val inflater = LayoutInflater.from(requireContext)
        bindingBorder = BordersDialogBinding.inflate(inflater)
        binding.toolbarSelectGrid.title = "Borders"
        binding.parentGrids.removeAllViews()
        bindingBorder.borderRadiusSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                viewModel.radius.postValue(progress.toFloat())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {

            }
        })
        bindingBorder.borderSeekBarSize.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                viewModel.borderSize.postValue(progress)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {

            }
        })
        bindingBorder.borderImageSizeSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                viewModel.imageSize.postValue(progress)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {

            }
        })
        binding.parentGrids.addView(bindingBorder.root)
    }
}