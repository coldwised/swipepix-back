package com.coldwised.di

import com.coldwised.data.ProductDataSource
import com.coldwised.data.ProductDataSourceImpl
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
    single<ProductDataSource> {
        ProductDataSourceImpl(
            get()
        )
    }
    single {
        RoomController(get())
    }
}