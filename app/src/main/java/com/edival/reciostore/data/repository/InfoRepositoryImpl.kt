package com.edival.reciostore.data.repository

import com.edival.reciostore.data.dataSource.remote.InfoRemoteDataSource
import com.edival.reciostore.domain.model.Info
import com.edival.reciostore.domain.repository.InfoRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InfoRepositoryImpl(private val remoteDS: InfoRemoteDataSource) : InfoRepository {
    override fun getInfo(): Flow<Resource<List<Info>>> = flow {
        emit(ResponseToRequest.send(remoteDS.getInfo()))
    }

    override suspend fun getInfoByKey(key: String): Resource<Info> {
        return ResponseToRequest.send(remoteDS.getInfoByKey(key))
    }
}