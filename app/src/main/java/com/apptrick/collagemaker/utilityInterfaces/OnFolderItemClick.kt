package com.apptrick.collagemaker.utilityInterfaces

import android.net.Uri

interface OnFolderItemClick {
    fun onClick(position: Int, folderName: String)

    fun onSelect(position: Int, uri: Uri)
}