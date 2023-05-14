package com.coldwised.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@kotlinx.serialization.Serializable
data class Category(
    @BsonId
    val id: String = ObjectId().toString(),
    val image: String? = null,
    val name: String,
    val parentId: String? = null,
    val childCategories: List<String>,
)