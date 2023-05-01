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
    suspend fun getAllProducts(): List<Product> {
        return productDataSource.getAllProducts()
    }

    private suspend fun fillTestCategory() {
        productDataSource.fillTestCategory()
    }

    suspend fun getAllCatalogCategories(): List<Category> {
        return productDataSource.getCatalogCategories()
    }
}