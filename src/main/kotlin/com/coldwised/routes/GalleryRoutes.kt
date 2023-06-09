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

fun Route.getProductById(roomController: RoomController) {
    get("/get_product_by_id") {
        val id = call.parameters["productId"] ?: return@get
        roomController.getProductById(id)?.let { product ->
            call.respond(
                HttpStatusCode.OK,
                product
            )
        } ?: run {
            call.respond(
                HttpStatusCode.NotFound,
            )
        }
    }
}

fun Route.getProductsByCategory(roomController: RoomController) {
    get("/get_products_by_category") {
        val categoryId = call.parameters["categoryId"] ?: return@get
        call.respond(
            HttpStatusCode.OK,
            roomController.getProductsByCategory(categoryId)
        )
    }
}

fun Route.getProductByQuery(roomController: RoomController) {
    get("/get_product_by_query") {
        val query = call.parameters["query"] ?: return@get
        call.respond(
            HttpStatusCode.OK,
            roomController.getProductByQuery(query)
        )
    }
}