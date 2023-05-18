package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.data.dataSource.remote.service.InfoService
import com.edival.reciostore.domain.model.Info
import retrofit2.Response

class InfoRemoteDataSourceImpl(private val infoService: InfoService) : InfoRemoteDataSource {
    override suspend fun getInfo(): Response<List<Info>> = infoService.getInfo()
    override suspend fun getInfoByKey(key: String): Response<Info> = infoService.getInfoByKey(key)
}