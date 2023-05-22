package com.apptrick.collagemaker.views

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.adapters.DashBoardAdapter
import com.apptrick.collagemaker.databinding.FragmentDashBoardBinding
import com.apptrick.collagemaker.viewModel.ImageViewModel
import kotlinx.coroutines.*

class DashBoard : Fragment() {

    private lateinit var binding: FragmentDashBoardBinding
    private lateinit var adapter: DashBoardAdapter

    private val viewModel: ImageViewModel by lazy{
        ViewModelProvider(requireActivity())[ImageViewModel::class.java]
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashBoardBinding.inflate(layoutInflater)

        binding.btncollage.setOnClickListener {
            findNavController().navigate(R.id.action_dashBoard_to_collageMaker)
        }

        binding.btnblur.setOnClickListener {
            findNavController().navigate(R.id.action_dashBoard_to_blurBackground)
        }

        binding.btnsquare.setOnClickListener {
            findNavController().navigate(R.id.action_dashBoard_to_squareFit)

        }

        /*
        ToolBar
         */
        binding.toolbar.setNavigationOnClickListener {
            ActionBarDrawerToggle(
                requireActivity(),
                binding.drawarlayout,
                binding.toolbar,
                R.string.nav_open,
                R.string.nav_close
            )
        }
        val check: Boolean = checkingPermission(requireContext())

        if(check)
        {
            GlobalScope.launch(Dispatchers.IO) {
                viewModel.setImages()
                requireActivity().runOnUiThread{
                    viewModel.imageData1.observe(viewLifecycleOwner)  { imageList->
                        adapter = DashBoardAdapter(requireContext(), imageList)
                        binding.recentRV.adapter = adapter
                        binding.recentRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    }
                }
            }
        }
        else
        {
            Toast.makeText(requireContext(), "Allow Camera & And Gallery Access", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    companion object{
        /*
    Getting Permissions to read and write in storage
    & Getting camera permissions
     */
        fun checkPermission(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        }

        fun requestPermission(context: Context) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                ),
                100
            )
        }
        fun checkingPermission(context: Context): Boolean {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkPermission(context)) {
                    return true
                } else {
                    requestPermission(context)
                }
            }
            else {
                return true
            }
            return false
        }
    }
}