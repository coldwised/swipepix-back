package com.coldwised.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Product(
    @BsonId
    val id: Int,
    val images: List<String>,
    val rating: Double?,
    val name: String,
    val description: String,
    val price: Float,
    val country: String,
    val categoryId: Int,
    val params: List<Pair<String, String>>
)