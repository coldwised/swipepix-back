package com.coldwised.di

import com.coldwised.data.ImageDataSource
import com.coldwised.data.ImageDataSourceImpl
import com.coldwised.room.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo
            .createClient()
            .coroutine
            .getDatabase("image_db")
    }
    single<ImageDataSource> {
        ImageDataSourceImpl(
            get()
        )
    }
    single {
        RoomController(get())
    }
}