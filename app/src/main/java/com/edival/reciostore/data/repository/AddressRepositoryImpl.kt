package com.edival.reciostore.data.repository

import android.util.Log
import com.edival.reciostore.data.dataSource.local.AddressLocalDataSource
import com.edival.reciostore.data.dataSource.remote.AddressRemoteDataSource
import com.edival.reciostore.data.mapper.toAddress
import com.edival.reciostore.data.mapper.toAddressEntity
import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.repository.AddressRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import com.edival.reciostore.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AddressRepositoryImpl(
    private val localDS: AddressLocalDataSource, private val remoteDS: AddressRemoteDataSource
) : AddressRepository {
    override fun getAddressByUser(idUser: String): Flow<Resource<List<Address>>> = flow {
        localDS.getAddressByUser(idUser).collect { addressEntities ->
            addressEntities.run {
                val addressLocalMap = this.map { addressEntity -> addressEntity.toAddress() }
                try {
                    ResponseToRequest.send(remoteDS.getAddressByUser(idUser)).run {
                        when (this) {
                            is Resource.Success -> {
                                val addressRemote = this.data
                                if (!isListEqual(addressRemote, addressLocalMap)) {
                                    localDS.insertAllAddress(addressRemote.map { address -> address.toAddressEntity() })
                                }
                                emit(Resource.Success(addressRemote))
                                Log.d("getAddressByUser", "LOCAL: $addressLocalMap")
                                Log.d("getAddressByUser", "REMOTE: $addressRemote")
                            }

                            is Resource.Failure -> emit(Resource.Success(addressLocalMap))
                            else -> emit(Resource.Success(addressLocalMap))
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Success(addressLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createAddress(address: Address): Resource<Address> {
        ResponseToRequest.send(remoteDS.createAddress(address)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.insertAddress(this.data.toAddressEntity())
                    Resource.Success(this.data)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun updateAddress(id: String, address: Address): Resource<Address> {
        ResponseToRequest.send(remoteDS.updateAddress(id, address)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.updateAddress(
                        id = this.data.id.orEmpty(),
                        address = this.data.address.orEmpty(),
                        neighborhood = this.data.neighborhood.orEmpty()
                    )
                    Resource.Success(this.data)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun deleteAddress(id: String): Resource<Unit> {
        ResponseToRequest.send(remoteDS.deleteAddress(id)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.deleteAddress(id)
                    Resource.Success(Unit)
                }

                else -> Resource.Failure()
            }
        }
    }
}