package com.edival.reciostore.data.dataSource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_bag")
data class ShoppingBagProductEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "price") val price: Double = 0.0,
    @ColumnInfo(name = "id_category") val id_category: String = "",
    @ColumnInfo(name = "img") val img: String = "",
    @ColumnInfo(name = "quantity") val quantity: Int = 1
)