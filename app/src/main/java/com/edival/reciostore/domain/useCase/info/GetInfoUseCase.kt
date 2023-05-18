package com.edival.reciostore.domain.useCase.info

import com.edival.reciostore.domain.model.Info
import com.edival.reciostore.domain.repository.InfoRepository
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetInfoUseCase(private val repository: InfoRepository) {
    operator fun invoke(): Flow<Resource<List<Info>>> = repository.getInfo()
}