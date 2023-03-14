package com.coldwised.routes

import com.coldwised.room.MemberAlreadyExistsException
import com.coldwised.room.RoomController
import com.coldwised.session.GallerySession
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach

fun Route.gallerySocket(roomController: RoomController) {
    webSocket("/gallery-socket") {
        val session = call.sessions.get<GallerySession>()
        if(session == null) {
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No session"))
            return@webSocket
        }
        try {
            roomController.onJoin(
                username = session.username,
                sessionId = session.sessionId,
                socket = this
            )
            incoming.consumeEach { frame ->
                if(frame is Frame.Text) {
                    roomController.sendImage(
                        senderUserName = session.username,
                        imageUrl = frame.readText()
                    )
                }
            }
        } catch (e: MemberAlreadyExistsException) {
            call.respond(
                HttpStatusCode.Conflict
            )
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            roomController.tryDisconnect(session.username)
        }
    }
}

fun Route.getAllImages(roomController: RoomController) {
    get("/images") {
        call.respond(
            HttpStatusCode.OK,
            roomController.getAllImages()
        )
    }
}