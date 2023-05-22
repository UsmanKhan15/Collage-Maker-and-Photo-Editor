package com.apptrick.collagemaker.editingCollageUtilityClasses

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import android.view.View
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class SavingImage(val context: Context) {

    /*
    Getting image in bitmap and storing it
     */
    fun getBitmapFromView(view: View): Bitmap? {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth, view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.layout(0, 0, view.width, view.height)
        Log.d("", "combineImages: width: " + view.width)
        Log.d("", "combineImages: height: " + view.height)
        view.draw(canvas)
        return bitmap
    }
//    fun SaveImage(finalBitmap: Bitmap) {
//        val root = Environment.getExternalStoragePublicDirectory(
//            Environment.DIRECTORY_PICTURES
//        ).toString()
//        val myDir = File("$root/saved_images")
//        myDir.mkdirs()
//        val generator = Random()
//        var n = 10000
//        n = generator.nextInt(n)
//        val fname = "Image-$n.jpg"
//        val file = File(myDir, fname)
//        if (file.exists()) file.delete()
//        try {
//            val out = FileOutputStream(file)
//            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
//            out.flush()
//            out.close()
//            Toast.makeText(context, "Image Saved Successfully", Toast.LENGTH_SHORT).show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        MediaScannerConnection.scanFile(context, arrayOf(file.toString()), null,
//            MediaScannerConnection.OnScanCompletedListener { path, uri ->
//                Log.i("ExternalStorage", "Scanned $path:")
//                Log.i("ExternalStorage", "-> uri=$uri")
//            })
//    }

    fun saveBitmap(imgBitmap: Bitmap, fileNameOpening: String) {
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US)
        val now = Date()
        val fileName = fileNameOpening + "_" + formatter.format(now) + ".jpg"
        val outStream: FileOutputStream
        try {
            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val saveDir = File("$path/HeartBeat/")
            if (!saveDir.exists()) saveDir.mkdirs()
            val fileDir = File(saveDir, fileName)
            outStream = FileOutputStream(fileDir)
            imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
            MediaScannerConnection.scanFile(
                context.getApplicationContext(), arrayOf(fileDir.toString()), null
            ) { path, uri -> }
            outStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}