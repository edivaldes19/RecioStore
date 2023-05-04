package com.edival.reciostore.data.dataSource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "address") val address: String = "",
    @ColumnInfo(name = "neighborhood") val neighborhood: String = "",
    @ColumnInfo(name = "id_user") val id_user: String = ""
)