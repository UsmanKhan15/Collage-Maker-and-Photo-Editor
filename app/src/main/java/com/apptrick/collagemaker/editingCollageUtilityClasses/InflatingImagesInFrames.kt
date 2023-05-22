package com.apptrick.collagemaker.editingCollageUtilityClasses

import android.R.attr
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.databinding.FragmentSelectCollageBinding
import com.apptrick.collagemaker.viewModel.ImageViewModel
import com.bumptech.glide.Glide
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import java.io.InputStream


class InflatingImagesInFrames {
    /*
    Inflating images when user select two images
     */
    @SuppressLint("ResourceAsColor")
    fun inflatingImages(
        binding: FragmentSelectCollageBinding, requireContext: Context,
        viewLifecycleOwner: LifecycleOwner,
        requireActivity: ViewModelStoreOwner, twoImagesDefault: Int
    ) {
        val viewModel: ImageViewModel by lazy {
            ViewModelProvider(requireActivity)[ImageViewModel::class.java]
        }
        binding.frameLayoutParent.removeAllViews()
        val twoImagesDefault: View = LayoutInflater.from(requireContext).inflate(
            twoImagesDefault,
            binding.root, false
        )
        val parentSize: LinearLayout = twoImagesDefault.findViewById(R.id.parentTwo)
        val image1: ImageView = twoImagesDefault.findViewById(R.id.image1)
        val image2: ImageView = twoImagesDefault.findViewById(R.id.image2)
        val cardView1: CardView = twoImagesDefault.findViewById(R.id.cardViewImg11)
        val cardView2: CardView = twoImagesDefault.findViewById(R.id.cardViewImg12)
        cardView1.setCardBackgroundColor(R.color.black)
        cardView2.setCardBackgroundColor(R.color.black)
        viewModel.radius.observe(viewLifecycleOwner) { value ->
            Log.d("Progress", "inflatingImages: " + value)
            cardView1.radius = value
            cardView2.radius = value
        }
        viewModel.borderSize.observe(viewLifecycleOwner) { value ->
            cardView1.setContentPadding(value, value, value, value)
            cardView2.setContentPadding(value, value, value, value)
        }
        viewModel.imageSize.observe(viewLifecycleOwner) { value ->
            parentSize.setPadding(value, value, value, value)
        }
        Glide.with(requireContext).load(viewModel.selectedImage[0]).into(image1)
        Glide.with(requireContext).load(viewModel.selectedImage[1]).into(image2)
        if (!OpenCVLoader.initDebug()) {
            Toast.makeText(requireContext, "Value not found ", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val imageStream: InputStream? = requireContext.getContentResolver().openInputStream(viewModel.selectedImage[0])
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val src = Mat(selectedImage.height, selectedImage.width, CvType.CV_8UC4)
            Utils.bitmapToMat(selectedImage, src)
            Imgproc.blur(src, src, Size(1.0, 3.0))
            Toast.makeText(requireContext, "Image Blured Successfully", Toast.LENGTH_SHORT).show()
            val img1 = Imgcodecs.imread(viewModel.selectedImage[0].toString())
            //Imgproc.applyColorMap(img1, img1, Imgproc.COLORMAP_SUMMER)
            //Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGBA, 0);
        }
        binding.frameLayoutParent.addView(twoImagesDefault)
    }
}