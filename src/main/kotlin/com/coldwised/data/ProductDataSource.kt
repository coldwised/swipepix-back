package com.coldwised.data

import com.coldwised.data.model.Category
import com.coldwised.data.model.Product

interface ProductDataSource {

    suspend fun getAllProducts(): List<Product>

    suspend fun insertProduct(product: Product)

    suspend fun fillTestCategory()

    suspend fun getCatalogCategories(): List<Category>
}