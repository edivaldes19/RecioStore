package com.edival.reciostore.domain.useCase.info

import com.edival.reciostore.domain.model.Info
import com.edival.reciostore.domain.repository.InfoRepository
import com.edival.reciostore.domain.util.Resource

class GetInfoByKeyUseCase(private val repository: InfoRepository) {
    suspend operator fun invoke(key: String): Resource<Info> = repository.getInfoByKey(key)
}