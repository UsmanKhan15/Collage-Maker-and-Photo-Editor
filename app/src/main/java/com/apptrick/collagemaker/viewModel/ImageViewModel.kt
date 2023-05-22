package com.apptrick.collagemaker.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.apptrick.collagemaker.dataclasses.ImageData

class ImageViewModel(application: Application): AndroidViewModel(application) {

    val imageData1: MutableLiveData<MutableList<ImageData>> by lazy { MutableLiveData() }
    var selectedImage = mutableListOf<Uri>()
    val folderList: ArrayList<String> by lazy { ArrayList() }
    val radius: MutableLiveData<Float> by lazy { MutableLiveData() }
    val borderSize: MutableLiveData<Int> by lazy { MutableLiveData() }
    val imageSize: MutableLiveData<Int> by lazy { MutableLiveData() }

    /*
    Getting images from internal storage into application
     */
    @SuppressLint("Range")
    suspend fun setImages() {
        val tempImages = mutableListOf<ImageData>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        )
        val selection: String? = null
        val selectionargs: Array<String>? = null
        val orderBy: String? = MediaStore.Video.Media.DATE_TAKEN
        val content_uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor? =
            getApplication<Application>().contentResolver.query(content_uri,
                projection, selection, selectionargs, orderBy + " DESC")
        val folderColumn =
            cursor?.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        if(!folderList.contains("All"))
        {
            folderList.add("All")
        }
        if (cursor != null) {
            cursor.moveToPosition(0)
            while (true) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))

                val folderName = folderColumn?.let { cursor.getString(it) }

                val image_uri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                tempImages.add(ImageData(image_uri, id.toString(), folderName.toString()))

                if(!folderList.contains(folderName))
                    folderList.add(folderName.toString())

                if (!cursor.isLast) cursor.moveToNext() else break
            }
            imageData1.postValue(tempImages)
        }
    }
}