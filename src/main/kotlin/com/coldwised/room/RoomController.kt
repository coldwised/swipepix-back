package com.coldwised.room

import com.coldwised.data.ProductDataSource
import com.coldwised.data.model.Category
import com.coldwised.data.model.Product

class RoomController(
    private val productDataSource: ProductDataSource
) {
    suspend fun getAllProducts(): List<Product> {
        return productDataSource.getAllProducts()
    }

    suspend fun getAllCatalogCategories(): List<Category> {
        return productDataSource.getCatalogCategories()
    }
}