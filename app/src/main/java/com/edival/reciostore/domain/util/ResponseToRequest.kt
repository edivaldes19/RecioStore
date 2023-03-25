package com.edival.reciostore.domain.util

import com.edival.reciostore.domain.model.ErrorResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object ResponseToRequest {
    fun <T> send(result: Response<T>): Resource<T> {
        return try {
            if (result.isSuccessful) Resource.Success(result.body()!!)
            else {
                val errorResponse: ErrorResponse? = ConvertErrorBody.convert(result.errorBody())
                Resource.Failure(errorResponse?.message ?: "Error desconocido.")
            }
        } catch (e: HttpException) {
            Resource.Failure(e.message ?: "Error en la petición.")
        } catch (e: IOException) {
            Resource.Failure(e.message ?: "Verífica tu conexión a internet.")
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "Error desconocido.")
        }
    }
}