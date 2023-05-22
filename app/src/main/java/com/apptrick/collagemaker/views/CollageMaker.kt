package com.apptrick.collagemaker.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apptrick.collagemaker.adapters.CollageImageAdapter
import com.apptrick.collagemaker.adapters.FolderAdapter
import com.apptrick.collagemaker.adapters.SelectedImagesAdapter
import com.apptrick.collagemaker.databinding.FragmentCollageMakerBinding
import com.apptrick.collagemaker.dataclasses.ImageData
import com.apptrick.collagemaker.utilityInterfaces.OnFolderItemClick
import com.apptrick.collagemaker.viewModel.ImageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CollageMaker : Fragment(), OnFolderItemClick {

    private lateinit var adapterImages: CollageImageAdapter
    private lateinit var adapterSelectedImages: SelectedImagesAdapter
    val tempImage = mutableListOf<ImageData>()
    private val binding by lazy{
        FragmentCollageMakerBinding.inflate(layoutInflater)
    }
    private val viewModel: ImageViewModel by lazy{
        ViewModelProvider(requireActivity())[ImageViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        Checking permissions to access camera and gallery
        & Getting images
         */
        if(DashBoard.checkPermission(requireContext()))
        {
            val adapter = FolderAdapter(requireContext(), viewModel.folderList, this@CollageMaker)
            binding.folderRecView.adapter = adapter
            binding.folderRecView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            viewModel.imageData1.observe(viewLifecycleOwner){listImages ->
                adapterCalling(listImages)
            }
        }
        else
        {
            Toast.makeText(requireContext(), "Allow access", Toast.LENGTH_SHORT).show()
        }

        /*
        Clicking on camera button to access mobile camera
         */
        binding.btnCamera.setOnClickListener{
            if(DashBoard.checkPermission(requireContext()))
            {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 101)
            }
        }

        binding.txtNext.setOnClickListener {
            val action = CollageMakerDirections.actionCollageMakerToSelectCollage()
            findNavController().navigate(action)
        }

        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    /*
    Setting images against each folder I implement this method from interface
     */
    override fun onClick(position: Int, folderName: String) {
        if(folderName.equals("All"))
        {
            viewModel.imageData1.observe(viewLifecycleOwner){ listImages->
                adapterCalling(listImages)
            }
        }
        else
        {
            viewModel.imageData1.observe(viewLifecycleOwner)
            {
                for (i in it){
                    if(folderName == i.folderName)
                    {
                        tempImage.add(ImageData(i.uri, i.id, i.folderName))
                    }
                }
            }
            adapterCalling(tempImage)
        }
    }

    //Adding selected images in selected recyclerview
    override fun onSelect(position: Int, uri: Uri) {
        checkingVisibility(uri)
        selectedImagesAdapterCalling()
    }

    /*
    OnResume function overriding
     */
    override fun onResume() {
        when {
            DashBoard.checkPermission(requireContext()) -> {
                CoroutineScope(Dispatchers.Default).launch {
                    checkVisibiltyOnResume()
                }
            }
        }
        super.onResume()
    }

    private fun checkVisibiltyOnResume() {
        when {
            viewModel.selectedImage.isEmpty() -> {
                binding.parentSelectedImages.visibility = View.INVISIBLE
            }
            viewModel.selectedImage.size > 1 -> {
                binding.txtNext.visibility = View.VISIBLE
                binding.parentSelectedImages.visibility = View.VISIBLE
                selectedImagesAdapterCalling()
            }
            else -> {
                if(viewModel.selectedImage.size <= 1){
                    binding.txtNext.visibility = View.INVISIBLE
                    selectedImagesAdapterCalling()
                    if(viewModel.selectedImage.size < 1){
                        binding.parentSelectedImages.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun selectedImagesAdapterCalling() {
        "Selected Picture 1-9 (${viewModel.selectedImage.size})".also { binding.txtSelectedImages.text = it }
        adapterSelectedImages = SelectedImagesAdapter(requireContext(), viewModel.selectedImage)
        binding.selectedImgRecView.adapter = adapterSelectedImages
        binding.selectedImgRecView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun checkingVisibility(uri: Uri) {
        if (viewModel.selectedImage.contains(uri)) {
            viewModel.selectedImage.remove(uri)
            if(viewModel.selectedImage.size <= 1){
                binding.txtNext.visibility = View.INVISIBLE
                if(viewModel.selectedImage.size < 1){
                    binding.parentSelectedImages.visibility = View.INVISIBLE
                }
            }
        }
        else {
            binding.parentSelectedImages.visibility = View.VISIBLE
            if(viewModel.selectedImage.size >= 1){
                binding.txtNext.visibility = View.VISIBLE
            }
            when {
                viewModel.selectedImage.size < 9 -> {
                    viewModel.selectedImage.add(uri)
                    binding.selectedImgRecView.visibility = View.VISIBLE
                }
                else -> Toast.makeText(requireContext(), "You can't select more then 9 images", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun adapterCalling(listData: MutableList<ImageData>){
        adapterImages = CollageImageAdapter(requireContext(), listData, this@CollageMaker)
        binding.collageImagesRecView.adapter = adapterImages
        binding.collageImagesRecView.layoutManager = GridLayoutManager(requireContext(), 4) }
}