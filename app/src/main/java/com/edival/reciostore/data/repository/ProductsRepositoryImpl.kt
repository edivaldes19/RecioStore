package com.edival.reciostore.data.repository

import android.net.Uri
import android.util.Log
import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataSource.local.ProductsLocalDataSource
import com.edival.reciostore.data.dataSource.remote.ProductsRemoteDataSource
import com.edival.reciostore.data.mapper.toProduct
import com.edival.reciostore.data.mapper.toProductEntity
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.model.ProductHasImages
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import com.edival.reciostore.domain.util.isListEqual
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class ProductsRepositoryImpl @Inject constructor(
    private val localDS: ProductsLocalDataSource,
    private val remoteDS: ProductsRemoteDataSource,
    private val storage: FirebaseStorage,
    @Named(Config.PRODUCTS_URL) private val storageProductsRef: StorageReference
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

    override fun getProductsByName(name: String): Flow<Resource<List<Product>>> = flow {
        emit(ResponseToRequest.send(remoteDS.getProductsByName(name)))
    }

    override suspend fun createProduct(product: Product, images: List<Uri>): Resource<Product> {
        try {
            val phiList = mutableListOf<ProductHasImages>()
            images.forEach { uri ->
                val currentTime = "${System.currentTimeMillis() / 1000}"
                val ref = storageProductsRef.child("${product.name}_$currentTime")
                ref.putFile(uri).await()
                ref.downloadUrl.await().also { dlUri ->
                    phiList.add(ProductHasImages(img_url = dlUri.toString()))
                }
            }
            product.phi = phiList
        } catch (e: Exception) {
            return if (!e.message.isNullOrBlank()) Resource.Failure(e.message!!) else Resource.Failure()
        }
        ResponseToRequest.send(remoteDS.createProduct(product)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.insertProduct(this.data.toProductEntity())
                    Resource.Success(this.data)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun updateProduct(
        id: String, product: Product, images: List<Uri>?
    ): Resource<Product> {
        try {
            if (!images.isNullOrEmpty()) {
                if (!product.phi.isNullOrEmpty()) {
                    product.phi!!.forEach { oldImages ->
                        if (!oldImages.img_url.isNullOrBlank()) {
                            val httpsReference = storage.getReferenceFromUrl(oldImages.img_url)
                            httpsReference.delete().await()
                        }
                    }
                }
                val phiList = mutableListOf<ProductHasImages>()
                images.forEach { uri ->
                    val currentTime = "${System.currentTimeMillis() / 1000}"
                    val ref = storageProductsRef.child("${product.name}_$currentTime")
                    ref.putFile(uri).await()
                    ref.downloadUrl.await().also { dlUri ->
                        phiList.add(ProductHasImages(img_url = dlUri.toString()))
                    }
                }
                product.phi = phiList
            }
        } catch (e: Exception) {
            return if (!e.message.isNullOrBlank()) Resource.Failure(e.message!!) else Resource.Failure()
        }
        ResponseToRequest.send(remoteDS.updateProduct(id, product)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.updateProduct(
                        id = this.data.id.orEmpty(),
                        name = this.data.name.orEmpty(),
                        description = this.data.description.orEmpty(),
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