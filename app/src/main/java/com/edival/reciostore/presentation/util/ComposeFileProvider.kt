package com.edival.reciostore.presentation.util

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.io.FileUtils
import java.io.File

class ComposeFileProvider : FileProvider(R.xml.file_paths) {
    companion object {
        suspend fun createFilesFromUris(
            ctx: Context,
            uriList: List<Uri>,
            data: suspend (zipFiles: List<File>, errMsg: String?) -> Unit
        ) {
            try {
                val fileList = mutableListOf<File>()
                uriList.forEach { uri ->
                    val stream = ctx.contentResolver.openInputStream(uri)
                    val file = withContext(Dispatchers.IO) {
                        File.createTempFile(
                            "${System.currentTimeMillis()}", Config.IMAGES_SUFFIX, ctx.cacheDir
                        )
                    }
                    FileUtils.copyInputStreamToFile(stream, file)
                    Compressor.compress(ctx, file) { size(Config.IMAGES_MAXSIZE) }.also { zipFile ->
                        fileList.add(zipFile)
                    }
                }
                data(fileList, null)
            } catch (e: Exception) {
                data(emptyList(), e.message ?: ctx.getString(R.string.unknown_error))
            }
        }
    }
}