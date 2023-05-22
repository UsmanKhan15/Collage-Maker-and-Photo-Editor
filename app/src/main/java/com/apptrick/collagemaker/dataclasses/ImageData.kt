package com.apptrick.collagemaker.dataclasses

import android.net.Uri
import java.io.Serializable

data class ImageData(
    val uri: Uri? = null,
    val id: String? = null,
    var folderName: String? = null
)