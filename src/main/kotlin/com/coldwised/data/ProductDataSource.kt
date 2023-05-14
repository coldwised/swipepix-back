package com.coldwised.data

import com.coldwised.data.model.Category
import com.coldwised.data.model.Product

interface ProductDataSource {

    suspend fun getProductsByCategory(categoryId: String): List<Product>

    suspend fun insertProduct(product: Product)

    suspend fun getCatalogCategories(): List<Category>

    suspend fun getChildCategories(parentId: String): List<Category>
}