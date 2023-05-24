package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.data.dataSource.remote.service.ProductsService
import com.edival.reciostore.domain.model.Product
import retrofit2.Response

class ProductsRemoteDataSourceImpl(private val productsService: ProductsService) :
    ProductsRemoteDataSource {
    override suspend fun getProducts(): Response<List<Product>> = productsService.getProducts()
    override suspend fun getProductsByCategory(id: String): Response<List<Product>> {
        return productsService.getProductsByCategory(id)
    }

    override suspend fun getProductsByName(name: String): Response<List<Product>> {
        return productsService.getProductsByName(name)
    }

    override suspend fun createProduct(product: Product): Response<Product> {
        return productsService.createProduct(product)
    }

    override suspend fun updateProduct(id: String, product: Product): Response<Product> {
        return productsService.updateProduct(id, product)
    }

    override suspend fun deleteProduct(id: String): Response<Product> {
        return productsService.deleteProduct(id)
    }
}