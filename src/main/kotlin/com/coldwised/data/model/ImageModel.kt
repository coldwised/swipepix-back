package com.coldwised.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@kotlinx.serialization.Serializable
data class ImageModel(
    val url: String,
    val timestamp: Long,
    val senderUsername: String,
    @BsonId
    val id: String = ObjectId().toString(),
)