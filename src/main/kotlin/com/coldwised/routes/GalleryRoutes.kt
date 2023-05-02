package com.coldwised.routes

import com.coldwised.room.RoomController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getCatalogCategories(roomController: RoomController) {
    get("/categories") {
        call.respond(
            HttpStatusCode.OK,
            roomController.getAllCatalogCategories()
        )
    }
}

fun Route.getChildCategories(roomController: RoomController) {
    get("/child_categories") {
        val parentId = call.parameters["parentId"] ?: return@get
        call.respond(
            HttpStatusCode.OK,
            roomController.getChildCategories(parentId)
        )
    }
}