package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.data.dataSource.remote.service.AddressService
import com.edival.reciostore.domain.model.Address
import retrofit2.Response

class AddressRemoteDataSourceImpl(private val addressService: AddressService) :
    AddressRemoteDataSource {
    override suspend fun getAddressByUser(idUser: String): Response<List<Address>> {
        return addressService.getAddressByUser(idUser)
    }

    override suspend fun createAddress(address: Address): Response<Address> {
        return addressService.createAddress(address)
    }

    override suspend fun updateAddress(id: String, address: Address): Response<Address> {
        return addressService.updateAddress(id, address)
    }

    override suspend fun deleteAddress(id: String): Response<Unit> {
        return addressService.deleteAddress(id)
    }
}