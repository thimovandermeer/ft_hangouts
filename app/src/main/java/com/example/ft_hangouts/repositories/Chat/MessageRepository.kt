package com.example.ft_hangouts.repositories.Chat

data class Message(
    val channelID: String,
    var sender: String,
    val receiver: String,
    val text: String,
    val isMine : Boolean
)

interface MessageRepository {
    suspend fun getMessages(channelID: String): List<Message>


}