package com.coldwised.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@kotlinx.serialization.Serializable
data class Category(
    @BsonId
    val id: String = ObjectId().toString(),
    val categoryImage: String,
    val name: String,
    val childCategories: List<Int>,
)