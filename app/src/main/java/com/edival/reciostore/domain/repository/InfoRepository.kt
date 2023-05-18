package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.Info
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface InfoRepository {
    fun getInfo(): Flow<Resource<List<Info>>>
    suspend fun getInfoByKey(key: String): Resource<Info>
}