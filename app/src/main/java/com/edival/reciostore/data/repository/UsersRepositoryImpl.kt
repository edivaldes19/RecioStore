package com.edival.reciostore.data.repository

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataSource.remote.UsersRemoteDataSource
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    private val remoteDS: UsersRemoteDataSource,
    private val storage: FirebaseStorage,
    @Named(Config.USERS_URL) private val storageUsersRef: StorageReference
) : UsersRepository {
    override fun getUsers(id: String): Flow<Resource<List<User>>> = flow {
        emit(ResponseToRequest.send(remoteDS.getUsers(id)))
    }

    override suspend fun updateUser(id: String, user: User, uri: Uri?): Resource<User> {
        try {
            uri?.let { photo ->
                if (!user.img.isNullOrBlank()) {
                    val httpsReference = storage.getReferenceFromUrl(user.img!!)
                    httpsReference.delete().await()
                }
                val currentTime = "${System.currentTimeMillis() / 1000}"
                val ref = storageUsersRef.child("${user.name}_$currentTime")
                ref.putFile(photo).await()
                ref.downloadUrl.await().also { dlUri -> user.img = dlUri.toString() }
            }
        } catch (e: Exception) {
            return if (!e.message.isNullOrBlank()) Resource.Failure(e.message!!) else Resource.Failure()
        }
        return ResponseToRequest.send(remoteDS.updateUser(id, user))
    }

    override suspend fun updateUserToClient(id: String): Resource<AuthResponse> {
        return ResponseToRequest.send(remoteDS.updateUserToClient(id))
    }

    override suspend fun updateUserToAdmin(id: String): Resource<AuthResponse> {
        return ResponseToRequest.send(remoteDS.updateUserToAdmin(id))
    }

    override suspend fun downloadUserImg(ctx: Context, url: String): Resource<Unit> {
        return try {
            val directory = File(
                "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}${File.separator}${
                    ctx.getString(R.string.app_name)
                }${File.separator}${Config.USERS_URL}"
            )
            if (!directory.exists()) directory.mkdirs()
            val imageFile = File(directory, "${System.currentTimeMillis()}.${Config.IMAGES_SUFFIX}")
            storage.getReferenceFromUrl(url).getFile(imageFile).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            if (!e.message.isNullOrBlank()) Resource.Failure(e.message!!) else Resource.Failure()
        }
    }
}