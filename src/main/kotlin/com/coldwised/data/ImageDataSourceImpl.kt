package com.coldwised.data

import com.coldwised.data.model.ImageModel
import org.litote.kmongo.coroutine.CoroutineDatabase

class ImageDataSourceImpl(
    private val db: CoroutineDatabase
): ImageDataSource {

    private val images = db.getCollection<ImageModel>()

    override suspend fun getAllImages(): List<ImageModel> {
        return images
            .find()
            .descendingSort(ImageModel::timestamp)
            .toList()
    }

    override suspend fun insertImage(image: ImageModel) {
        images.insertOne(image)
    }
}