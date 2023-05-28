package com.coldwised.plugins

import com.coldwised.room.RoomController
import com.coldwised.routes.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    install(Routing) {
        getCatalogCategories(roomController)
        getChildCategories(roomController)
        getProductsByCategory(roomController)
        getProductById(roomController)
        getProductByQuery(roomController)
    }
}
