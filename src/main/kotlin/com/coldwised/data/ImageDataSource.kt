package com.coldwised.data

import com.coldwised.data.model.ImageModel

interface ImageDataSource {

    suspend fun getAllImages(): List<ImageModel>

    suspend fun insertImage(image: ImageModel)
}