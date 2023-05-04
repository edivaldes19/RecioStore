package com.edival.reciostore.domain.useCase.address

import com.edival.reciostore.domain.repository.AddressRepository
import com.edival.reciostore.domain.util.Resource

class DeleteAddressUseCase(private val addressRepository: AddressRepository) {
    suspend operator fun invoke(id: String): Resource<Unit> {
        return addressRepository.deleteAddress(id)
    }
}