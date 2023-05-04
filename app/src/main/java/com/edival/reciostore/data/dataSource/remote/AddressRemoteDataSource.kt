package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.Address
import retrofit2.Response

interface AddressRemoteDataSource {
    suspend fun getAddressByUser(idUser: String): Response<List<Address>>
    suspend fun createAddress(address: Address): Response<Address>
    suspend fun updateAddress(id: String, address: Address): Response<Address>
    suspend fun deleteAddress(id: String): Response<Unit>
}