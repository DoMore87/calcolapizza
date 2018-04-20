package com.domore.calcolapizza

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import android.view.View
import java.io.File
import java.io.FileOutputStream

object ScreenshotUtils {
    fun getScreenShot(view: View): Bitmap {
        val screenView = view.rootView
        screenView.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(screenView.getDrawingCache())
        screenView.isDrawingCacheEnabled = false
        return bitmap
    }

    fun getMainDirectoryName(): File {
        val mainDir = File(Environment.getExternalStorageDirectory().toString() + "/Calcolapizza")
        if (!mainDir.exists()) {
            if (mainDir.mkdir())
                Log.e("Create Directory", "Main Directory Created : $mainDir")
        }
        return mainDir
    }

    fun store(bm: Bitmap, fileName: String, saveFilePath: File): File {
        val dir = File(saveFilePath.absolutePath)
        if (!dir.exists())
            dir.mkdirs()
        val file = File(saveFilePath.absolutePath, fileName)
        try {
            val fOut = FileOutputStream(file)
            bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut)
            fOut.flush()
            fOut.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file
    }
}