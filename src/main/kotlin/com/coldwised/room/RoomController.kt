package com.coldwised.room

import com.coldwised.data.ImageDataSource
import com.coldwised.data.model.ImageModel
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(
    private val imageDataSource: ImageDataSource
) {
    private val members = ConcurrentHashMap<String, Member>()

    fun onJoin(
        username: String,
        sessionId: String,
        socket: WebSocketSession,
    ) {
        if(members.containsKey(username)) {
            throw MemberAlreadyExistsException()
        }
        members[username] = Member(
            username = username,
            sessionId = sessionId,
            socket = socket
        )
    }

    suspend fun sendImage(senderUserName: String, imageUrl: String) {
        members.values.forEach { member ->
            val imageEntity = ImageModel(
                url = imageUrl,
                senderUsername = senderUserName,
                timestamp = System.currentTimeMillis()
            )
            imageDataSource.insertImage(imageEntity)

            val parsedImage = Json.encodeToString(imageEntity)
            member.socket.send(Frame.Text(parsedImage))
        }
    }

    suspend fun getAllImages(): List<ImageModel> {
        return imageDataSource.getAllImages()
    }

    suspend fun tryDisconnect(username: String) {
        members[username]?.socket?.close()
        if(members.containsKey(username)) {
            members.remove(username)
        }
    }
}