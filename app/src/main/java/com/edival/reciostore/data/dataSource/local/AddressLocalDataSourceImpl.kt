package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.dao.AddressDAO
import com.edival.reciostore.data.dataSource.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

class AddressLocalDataSourceImpl(private val addressDAO: AddressDAO) : AddressLocalDataSource {
    override suspend fun insertAddress(address: AddressEntity) = addressDAO.insertAddress(address)
    override suspend fun insertAllAddress(addressList: List<AddressEntity>) {
        return addressDAO.insertAllAddress(addressList)
    }

    override fun getAddressByUser(idUser: String): Flow<List<AddressEntity>> {
        return addressDAO.getAddressByUser(idUser)
    }

    override suspend fun updateAddress(id: String, address: String, neighborhood: String) {
        return addressDAO.updateAddress(id, address, neighborhood)
    }

    override suspend fun deleteAddress(id: String) = addressDAO.deleteAddress(id)
}