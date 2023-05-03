package com.coldwised.room

import com.coldwised.data.ProductDataSource
import com.coldwised.data.model.Category
import com.coldwised.data.model.Product
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomController(
    private val productDataSource: ProductDataSource
) {

    init {
        GlobalScope.launch {
            fillTestCategory()
        }
    }
    suspend fun getProductsByCategory(categoryId: String): List<Product> {
        return productDataSource.getProductsByCategory(categoryId)
    }

    private suspend fun fillTestCategory() {
        productDataSource.fillTestCategory()
    }

    suspend fun getAllCatalogCategories(): List<Category> {
        return productDataSource.getCatalogCategories()
    }

    suspend fun getChildCategories(parentId: String): List<Category> {
        return productDataSource.getChildCategories(parentId)
    }
}