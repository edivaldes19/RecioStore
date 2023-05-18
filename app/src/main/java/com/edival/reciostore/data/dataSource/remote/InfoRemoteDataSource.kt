package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.Info
import retrofit2.Response

interface InfoRemoteDataSource {
    suspend fun getInfo(): Response<List<Info>>
    suspend fun getInfoByKey(key: String): Response<Info>
}