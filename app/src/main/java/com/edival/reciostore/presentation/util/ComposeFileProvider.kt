package com.edival.reciostore.presentation.util

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

class ComposeFileProvider : FileProvider(R.xml.file_paths) {
    companion object {
        fun createFileFromUri(context: Context, uri: Uri): File? {
            return try {
                val stream = context.contentResolver.openInputStream(uri)
                val file = File.createTempFile(
                    "${System.currentTimeMillis()}", Config.SUFFIX_IMG, context.cacheDir
                )
                FileUtils.copyInputStreamToFile(stream, file)
                file
            } catch (e: Exception) {
                null
            }
        }

        fun getPathFromBitmap(context: Context, bitmap: Bitmap): String {
            val wrapper = ContextWrapper(context)
            var file = wrapper.getDir(Config.PATH_IMAGES, Context.MODE_PRIVATE)
            file = File(file, "${UUID.randomUUID()}${Config.SUFFIX_IMG}")
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            return file.path
        }
    }
}