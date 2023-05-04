package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

interface AddressLocalDataSource {
    suspend fun insertAddress(address: AddressEntity)
    suspend fun insertAllAddress(addressList: List<AddressEntity>)
    fun getAddressByUser(idUser: String): Flow<List<AddressEntity>>
    suspend fun updateAddress(id: String, address: String, neighborhood: String)
    suspend fun deleteAddress(id: String)
}