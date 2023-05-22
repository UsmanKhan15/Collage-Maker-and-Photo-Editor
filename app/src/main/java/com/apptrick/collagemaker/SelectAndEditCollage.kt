package com.apptrick.collagemaker

import android.R.attr.src
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apptrick.collagemaker.adapters.CollageDesignAdapters
import com.apptrick.collagemaker.adapters.EditingAdapter
import com.apptrick.collagemaker.databinding.EditingOptionsLayoutBinding
import com.apptrick.collagemaker.databinding.FragmentSelectCollageBinding
import com.apptrick.collagemaker.databinding.FrameSelectionViewBinding
import com.apptrick.collagemaker.editingCollageUtilityClasses.*
import com.apptrick.collagemaker.utilityInterfaces.OnFrameItemClick
import com.apptrick.collagemaker.viewModel.ImageViewModel
import org.opencv.android.Utils
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import java.io.InputStream


class SelectAndEditCollage : Fragment(), OnFrameItemClick {
    private val binding by lazy {
        FragmentSelectCollageBinding.inflate(layoutInflater)
    }
    private val bindingEditingOptions by lazy {
        EditingOptionsLayoutBinding.inflate(layoutInflater)
    }
    private val bindingFrameSelection by lazy {
        FrameSelectionViewBinding.inflate(layoutInflater)
    }
    private val viewModel: ImageViewModel by lazy {
        ViewModelProvider(requireActivity())[ImageViewModel::class.java]
    }
    private var listFrame: ArrayList<Int> = ArrayList()
    private lateinit var adapter: CollageDesignAdapters
    private val editingCollage: EditingCollage by lazy {
        EditingCollage()
    }
    private val borderEditing: BorderEditing by lazy {
        BorderEditing(requireActivity())
    }
    private val inflatingImagesInFrame: InflatingImagesInFrames by lazy {
        InflatingImagesInFrames()
    }
    private val ratioOptions: RatioOptions by lazy {
        RatioOptions()
    }
    private val savingImage: SavingImage by lazy {
        SavingImage(requireContext())
    }
    private val addingText: AddingText by lazy {
        AddingText()
    }
    private lateinit var adapterOptions: EditingAdapter
    private var detailEdit: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        settingGridDesign()
        detailEdit = false
        binding.txtDone.setOnClickListener {
            editingOptions()
            binding.toolbarSelectGrid.title = "Perform Editing"
            binding.txtSave.visibility = VISIBLE
        }

        binding.toolbarSelectGrid.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        inflatingImagesInFrame.inflatingImages(
            binding, requireContext(),
            viewLifecycleOwner, requireActivity(), R.layout.two_images_default
        )

        binding.txtSave.setOnClickListener {
            val image = savingImage.getBitmapFromView(binding.frameLayoutParent)
            image?.let { it1 -> savingImage.saveBitmap(it1, "Okk") }
        }
        return binding.root
    }
    private fun settingAdapter(listFrame: ArrayList<Int>) {
        binding.parentGrids.removeAllViews()
        adapter = CollageDesignAdapters(requireContext(), listFrame, this@SelectAndEditCollage)
        bindingFrameSelection.gridsRecView.adapter = adapter
        bindingFrameSelection.gridsRecView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.parentGrids.addView(bindingFrameSelection.root)
        detailEdit = true
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            detailEdit = false
            findNavController().navigateUp()
        }
    }
    override fun onFrameClick(position: Int) {
        when (position) {
            0 -> {
                binding.frameLayoutParent.removeAllViews()
                inflatingImagesInFrame.inflatingImages(
                    binding, requireContext(),
                    viewLifecycleOwner, requireActivity(), R.layout.two_images_default
                )
            }
            1 -> {
                binding.frameLayoutParent.removeAllViews()
                inflatingImagesInFrame.inflatingImages(
                    binding, requireContext(),
                    viewLifecycleOwner, requireActivity(), R.layout.two_images_vertical
                )
            }
            else -> {
                binding.frameLayoutParent.removeAllViews()
            }
        }
    }
    //Editing Options
    override fun onEditOptionClick(position: Int) {
        when (position) {
            0 -> {
                detailEdit = true
                borderEditing.borderOptions(binding, requireContext())
                backPress()
            }
            1 -> {
                detailEdit = true
                ratioOptions.ratioOptions(binding, requireContext())
                backPress()
            }
            2 -> {
                detailEdit = true
                addingText.addingText(binding, requireContext())
                backPress()
            }
            3 ->
            {

            }
            4 ->
            {

            }
            5 ->
            {

            }
            6 ->
            {

            }
            7 ->
            {

            }
            else -> {

            }
        }
    }
    //Showing frame according to user selected images
    private fun settingGridDesign() {
        when (viewModel.selectedImage.size) {
            2 -> {
                listFrame = editingCollage.addingFrame(2)
                settingAdapter(listFrame)
            }
            3 -> {
                listFrame = editingCollage.addingFrame(3)
                settingAdapter(listFrame)
            }
            4 -> {
                listFrame = editingCollage.addingFrame(4)
                settingAdapter(listFrame)
            }
            5 -> {
                listFrame = editingCollage.addingFrame(5)
                settingAdapter(listFrame)
            }
            6 -> {
                listFrame = editingCollage.addingFrame(6)
                settingAdapter(listFrame)
            }
            7 -> {
                listFrame = editingCollage.addingFrame(7)
                settingAdapter(listFrame)
            }
            8 -> {

            }
            else -> {

            }
        }
    }
    private fun editingOptions() {
        binding.toolbarSelectGrid.title = "Perform Editing"
        binding.parentGrids.removeAllViews()
        adapterOptions = EditingAdapter(
            requireContext(),
            editingCollage.addingEditingOptions(), this@SelectAndEditCollage
        )
        bindingEditingOptions.editingRecView.adapter = adapterOptions
        bindingEditingOptions.editingRecView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )

        binding.parentGrids.addView(bindingEditingOptions.root)
        detailEdit = true
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            detailEdit = false
            settingGridDesign()
        }
    }
    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            detailEdit = false
            editingOptions()
        }
    }
}