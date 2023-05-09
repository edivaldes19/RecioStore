package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataSource.remote.service.ProductsService
import com.edival.reciostore.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class ProductsRemoteDataSourceImpl(private val productsService: ProductsService) :
    ProductsRemoteDataSource {
    override suspend fun getProducts(): Response<List<Product>> = productsService.getProducts()
    override suspend fun getProductsByCategory(id: String): Response<List<Product>> {
        return productsService.getProductsByCategory(id)
    }

    override suspend fun getProductsByName(name: String): Response<List<Product>> {
        return productsService.getProductsByName(name)
    }

    override suspend fun createProduct(files: List<File>, product: Product): Response<Product> {
        val images = arrayOfNulls<MultipartBody.Part>(files.size)
        files.forEachIndexed { index, file ->
            val connection = withContext(Dispatchers.IO) { file.toURI().toURL().openConnection() }
            val mimeType = connection.contentType
            val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
            images[index] = MultipartBody.Part.createFormData("files[]", file.name, requestFile)
        }
        val nameData = product.name!!.toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        val descriptionData =
            product.description!!.toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        val idCategoryData = product.id_category!!.toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        val priceData = product.price.toString().toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        return productsService.createProduct(
            images, nameData, descriptionData, idCategoryData, priceData
        )
    }

    override suspend fun updateProduct(id: String, product: Product): Response<Product> {
        return productsService.updateProduct(id, product)
    }

    override suspend fun updateProductImages(
        files: List<File>, id: String, product: Product
    ): Response<Product> {
        val images = arrayOfNulls<MultipartBody.Part>(files.size)
        val imagesToUpdate = arrayOfNulls<RequestBody>(product.images_to_update?.size ?: 0)
        files.forEachIndexed { index, file ->
            val connection = withContext(Dispatchers.IO) { file.toURI().toURL().openConnection() }
            val mimeType = connection.contentType
            val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
            images[index] = MultipartBody.Part.createFormData("files[]", file.name, requestFile)
        }
        product.images_to_update?.forEachIndexed { index, pos ->
            imagesToUpdate[index] = pos.toString().toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        }
        val nameData = product.name!!.toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        val descriptionData =
            product.description!!.toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        val idCategoryData = product.id_category!!.toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        val priceData = product.price.toString().toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        return productsService.updateProductImages(
            id, images, nameData, descriptionData, idCategoryData, priceData, imagesToUpdate
        )
    }

    override suspend fun deleteProduct(id: String): Response<Unit> {
        return productsService.deleteProduct(id)
    }
}