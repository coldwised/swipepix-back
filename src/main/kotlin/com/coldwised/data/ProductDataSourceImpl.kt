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

    override suspend fun getProductsByCategory(categoryId: String): List<Product> {
        return products.find(Product::id eq categoryId).toList()
    }

    override suspend fun insertProduct(product: Product) {
        products.insertOne(product)
    }

    override suspend fun fillTestCategory() {
        categories.deleteMany()
        categories.insertMany(
            listOf(
                Category(
                    image = "https://static.svyaznoy.ru/upload/files/image/article-action-detail-listing-image-id/670/1.png",
                    name = "Смартфоны и гаджеты",
                    childCategories = listOf("abc, abc2")
                ),
                Category(
                    id = "abc",
                    image = "https://photo.brestcity.com/2022/07/smart1.jpg",
                    name = "category name 2",
                    childCategories = listOf()
                ),
                Category(
                    id = "abc2",
                    image = "https://photo.brestcity.com/2022/07/smart1.jpg",
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