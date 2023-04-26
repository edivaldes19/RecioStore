package com.edival.reciostore.domain.useCase.products

import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource
import java.io.File

class CreateProductUseCase(private val repository: ProductsRepository) {
    suspend operator fun invoke(files: List<File>, product: Product): Resource<Product> {
        return repository.createProduct(files, product)
    }
}