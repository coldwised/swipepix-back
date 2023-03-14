package com.coldwised.plugins

import com.coldwised.session.GallerySession
import io.ktor.server.sessions.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.application.ApplicationCallPipeline.ApplicationPhase.Plugins
import io.ktor.server.routing.*
import io.ktor.util.*

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<GallerySession>("SESSION")
    }

    intercept(Plugins) {
        if(call.sessions.get<GallerySession>() == null) {
            val username = call.parameters["username"] ?: "guest"
            call.sessions.set(GallerySession(username, generateNonce()))
        }
    }
}
