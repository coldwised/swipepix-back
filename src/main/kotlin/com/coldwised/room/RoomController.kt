package com.coldwised.room

import com.coldwised.data.ProductDataSource
import com.coldwised.data.model.Category
import com.coldwised.data.model.Product

class RoomController(
    private val productDataSource: ProductDataSource
) {
    suspend fun getProductsByCategory(categoryId: String): List<Product> {
        return productDataSource.getProductsByCategory(categoryId)
    }

    suspend fun getAllCatalogCategories(): List<Category> {
        return productDataSource.getCatalogCategories()
    }

    suspend fun getChildCategories(parentId: String): List<Category> {
        return productDataSource.getChildCategories(parentId)
    }

    suspend fun getProductByQuery(query: String): List<Product> {
        return productDataSource.getProductByQuery(query)
    }

    suspend fun getProductById(id: String): Product? {
        return productDataSource.getProductById(id)
    }
}