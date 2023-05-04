package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getAddressByUser(idUser: String): Flow<Resource<List<Address>>>
    suspend fun createAddress(address: Address): Resource<Address>
    suspend fun updateAddress(id: String, address: Address): Resource<Address>
    suspend fun deleteAddress(id: String): Resource<Unit>
}