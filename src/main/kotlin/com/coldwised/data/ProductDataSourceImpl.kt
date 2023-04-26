package com.coldwised.data

import com.coldwised.data.model.Category
import com.coldwised.data.model.Product
import org.litote.kmongo.coroutine.CoroutineDatabase

class ProductDataSourceImpl(
    private val db: CoroutineDatabase
): ProductDataSource {

    private val products = db.getCollection<Product>()
    private val categories = db.getCollection<Category>()

    override suspend fun getAllProducts(): List<Product> {
        return products.find().toList()
    }

    override suspend fun insertProduct(product: Product) {
        products.insertOne(product)
    }

    override suspend fun getCatalogCategories(): List<Category> {
        return categories.find().toList()
    }
}