package com.edival.reciostore.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edival.reciostore.data.dataSource.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: AddressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAddress(addressList: List<AddressEntity>)

    @Query("SELECT * FROM address WHERE id_user = :id_user")
    fun getAddressByUser(id_user: String): Flow<List<AddressEntity>>

    @Query("UPDATE address SET address = :address, neighborhood = :neighborhood WHERE id = :id")
    suspend fun updateAddress(id: String, address: String, neighborhood: String)

    @Query("DELETE FROM address WHERE id = :id")
    suspend fun deleteAddress(id: String)
}