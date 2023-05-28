package com.coldwised.data

import com.coldwised.data.model.Category
import com.coldwised.data.model.Product
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.regex

class ProductDataSourceImpl(
    db: CoroutineDatabase
): ProductDataSource {

    private val products = db.getCollection<Product>()
    private val categories = db.getCollection<Category>()

    override suspend fun getProductsByCategory(categoryId: String): List<Product> {
        return products.find(Product::categoryId eq categoryId).toList()
    }

    override suspend fun insertProduct(product: Product) {
        products.insertOne(product)
    }

    override suspend fun getCatalogCategories(): List<Category> {
        return categories.find(Category::parentId eq null).toList()
    }

    override suspend fun getProductById(id: String): Product? {
        return products.findOne(Product::id eq id)
    }

    override suspend fun getProductByQuery(query: String): List<Product> {
        return products.find(Product::name regex query).toList()
    }

    override suspend fun getChildCategories(parentId: String): List<Category> {
        return categories.find(Category::parentId eq parentId).toList()
    }
}