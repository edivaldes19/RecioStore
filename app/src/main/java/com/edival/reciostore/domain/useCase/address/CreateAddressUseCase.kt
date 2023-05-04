package com.edival.reciostore.domain.useCase.address

import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.repository.AddressRepository
import com.edival.reciostore.domain.util.Resource

class CreateAddressUseCase(private val repository: AddressRepository) {
    suspend operator fun invoke(address: Address): Resource<Address> {
        return repository.createAddress(address)
    }
}