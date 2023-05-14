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
        val sdf = products.find(Product::categoryId eq categoryId).toList()
        val a = sdf
        return products.find(Product::categoryId eq categoryId).toList()
    }

    override suspend fun insertProduct(product: Product) {
        products.insertOne(product)
    }

    override suspend fun getCatalogCategories(): List<Category> {
        return categories.find(Category::parentId eq null).toList()
    }

    override suspend fun getChildCategories(parentId: String): List<Category> {
        return categories.find(Category::parentId eq parentId).toList()
    }
}