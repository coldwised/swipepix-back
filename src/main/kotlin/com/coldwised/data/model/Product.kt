package com.coldwised.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Product(
    @BsonId
    val id: String = ObjectId().toString(),
    val images: List<String>,
    val rating: Float? = null,
    val name: String,
    val description: String,
    val price: Float,
    val country: String,
    val categoryId: Int,
    val params: List<Pair<String, String>>
)