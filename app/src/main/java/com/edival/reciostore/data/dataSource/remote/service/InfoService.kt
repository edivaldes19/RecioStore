package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Info
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InfoService {
    @GET(Config.INFO_URL)
    suspend fun getInfo(): Response<List<Info>>

    @GET("${Config.INFO_URL}/getInfoByKey/{key}")
    suspend fun getInfoByKey(@Path("key") key: String): Response<Info>
}