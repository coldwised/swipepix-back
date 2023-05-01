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

    override suspend fun fillTestCategory() {
        categories.deleteMany()
        categories.insertMany(
            listOf(
                Category(
                    categoryImage = "",
                    name = "Смартфоны и гаджеты",
                    childCategories = listOf(1)
                ),
                Category(
                    id = 1,
                    categoryImage = "",
                    name = "category name 2",
                    childCategories = listOf()
                ),
                Category(
                    id = 2,
                    categoryImage = "",
                    name = "category name 3",
                    childCategories = listOf()
                )
            )
        )
    }

    override suspend fun getCatalogCategories(): List<Category> {
        return categories.find().toList()
    }
}