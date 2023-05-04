package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Address
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AddressService {
    @GET("${Config.ADDRESS_URL}/getAddressByUser/{id_user}")
    suspend fun getAddressByUser(@Path("id_user") idUser: String): Response<List<Address>>

    @POST("${Config.ADDRESS_URL}/createAddress")
    suspend fun createAddress(@Body address: Address): Response<Address>

    @PUT("${Config.ADDRESS_URL}/updateAddress/{id}")
    suspend fun updateAddress(@Path("id") id: String, @Body address: Address): Response<Address>

    @DELETE("${Config.ADDRESS_URL}/deleteAddress/{id}")
    suspend fun deleteAddress(@Path("id") id: String): Response<Unit>
}