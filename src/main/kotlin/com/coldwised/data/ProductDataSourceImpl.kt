package com.coldwised.data

import com.coldwised.data.model.Category
import com.coldwised.data.model.Product
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ProductDataSourceImpl(
    db: CoroutineDatabase
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
                    categoryImage = "",
                    name = "category name 2",
                    childCategories = listOf()
                ),
                Category(
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

    override suspend fun getChildCategories(parentId: String): List<Category> {
        return categories.find(Category::id eq parentId).toList()
    }
}