package com.coldwised.plugins

import com.coldwised.room.RoomController
import com.coldwised.routes.gallerySocket
import com.coldwised.routes.getAllImages
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    install(Routing) {
        gallerySocket(roomController)
        getAllImages(roomController)
    }
}
