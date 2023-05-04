package com.edival.reciostore.domain.useCase.address

import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.repository.AddressRepository
import com.edival.reciostore.domain.util.Resource

class UpdateAddressUseCase(private val addressRepository: AddressRepository) {
    suspend operator fun invoke(id: String, address: Address): Resource<Address> {
        return addressRepository.updateAddress(id, address)
    }
}