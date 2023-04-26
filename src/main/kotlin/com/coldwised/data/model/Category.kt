package com.coldwised.data.model

import org.bson.codecs.pojo.annotations.BsonId

@kotlinx.serialization.Serializable
data class Category(
    @BsonId
    val id: Int,
    val categoryImage: String,
    val name: String,
    val childCategories: List<Int>,
)