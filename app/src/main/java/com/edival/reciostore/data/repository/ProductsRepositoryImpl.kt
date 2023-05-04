package com.edival.reciostore.data.repository

import android.util.Log
import com.edival.reciostore.data.dataSource.local.ProductsLocalDataSource
import com.edival.reciostore.data.dataSource.remote.ProductsRemoteDataSource
import com.edival.reciostore.data.mapper.toProduct
import com.edival.reciostore.data.mapper.toProductEntity
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import com.edival.reciostore.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class ProductsRepositoryImpl(
    private val localDS: ProductsLocalDataSource, private val remoteDS: ProductsRemoteDataSource
) : ProductsRepository {
    override fun getProducts(): Flow<Resource<List<Product>>> = flow {
        localDS.getProducts().collect { productEntities ->
            productEntities.run {
                val productsLocalMap = this.map { productEntity -> productEntity.toProduct() }
                try {
                    ResponseToRequest.send(remoteDS.getProducts()).run {
                        when (this) {
                            is Resource.Success -> {
                                val productsRemote = this.data
                                if (!isListEqual(productsRemote, productsLocalMap)) {
                                    localDS.insertAllProducts(productsRemote.map { product -> product.toProductEntity() })
                                }
                                emit(Resource.Success(productsRemote))
                                Log.d("getProducts", "LOCAL: $productsLocalMap")
                                Log.d("getProducts", "REMOTE: $productsRemote")
                            }

                            is Resource.Failure -> emit(Resource.Success(productsLocalMap))
                            else -> emit(Resource.Success(productsLocalMap))
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Success(productsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getProductsByCategory(id: String): Flow<Resource<List<Product>>> = flow {
        localDS.getProductsByCategory(id).collect { productEntities ->
            productEntities.run {
                val productsLocalMap = this.map { productEntity -> productEntity.toProduct() }
                try {
                    ResponseToRequest.send(remoteDS.getProductsByCategory(id)).run {
                        when (this) {
                            is Resource.Success -> {
                                val productsRemote = this.data
                                if (!isListEqual(productsRemote, productsLocalMap)) {
                                    localDS.insertAllProducts(productsRemote.map { product -> product.toProductEntity() })
                                }
                                emit(Resource.Success(productsRemote))
                                Log.d("getProductsByCategory", "LOCAL: $productsLocalMap")
                                Log.d("getProductsByCategory", "REMOTE: $productsRemote")
                            }

                            is Resource.Failure -> emit(Resource.Success(productsLocalMap))
                            else -> emit(Resource.Success(productsLocalMap))
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Success(productsLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

//    override fun findByName(name: String): Flow<Resource<List<Product>>> = flow {
//        emit(ResponseToRequest.send(remoteDS.findByName(name)))
//    }

    override suspend fun createProduct(files: List<File>, product: Product): Resource<Product> {
        ResponseToRequest.send(remoteDS.createProduct(files, product)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.insertProduct(this.data.toProductEntity())
                    Resource.Success(this.data)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun updateProduct(id: String, product: Product): Resource<Product> {
        ResponseToRequest.send(remoteDS.updateProduct(id, product)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.updateProduct(
                        id = this.data.id.orEmpty(),
                        name = this.data.name.orEmpty(),
                        description = this.data.description.orEmpty(),
                        img1 = this.data.img1.orEmpty(),
                        img2 = this.data.img2.orEmpty(),
                        price = this.data.price
                    )
                    Resource.Success(this.data)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun updateProductImages(
        files: List<File>, id: String, product: Product
    ): Resource<Product> {
        ResponseToRequest.send(remoteDS.updateProductImages(files, id, product)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.updateProduct(
                        id = this.data.id.orEmpty(),
                        name = this.data.name.orEmpty(),
                        description = this.data.description.orEmpty(),
                        img1 = this.data.img1.orEmpty(),
                        img2 = this.data.img2.orEmpty(),
                        price = this.data.price
                    )
                    Resource.Success(this.data)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun deleteProduct(id: String): Resource<Unit> {
        ResponseToRequest.send(remoteDS.deleteProduct(id)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.deleteProduct(id)
                    Resource.Success(Unit)
                }

                else -> Resource.Failure()
            }
        }
    }
}