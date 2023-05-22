package com.apptrick.collagemaker.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.databinding.FragmentSplashScreenBinding


class SplashScreen : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSplashScreenBinding.inflate(layoutInflater)

        binding.txtSplash.setOnClickListener {
            findNavController().navigate(R.id.action_splashScreen_to_dashBoard)
            FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
        }

        return binding.root
    }

}