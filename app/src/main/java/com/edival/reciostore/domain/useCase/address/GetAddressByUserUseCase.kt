package com.edival.reciostore.domain.useCase.address

import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.repository.AddressRepository
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetAddressByUserUseCase(private val repository: AddressRepository) {
    operator fun invoke(idUser: String): Flow<Resource<List<Address>>> {
        return repository.getAddressByUser(idUser)
    }
}