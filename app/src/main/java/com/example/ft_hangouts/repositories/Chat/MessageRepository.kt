package com.example.ft_hangouts.repositories.Chat

data class Message(
    val channelId: String,
    var sender: String,
    val receiver: String,
    val text: String,
    val isMine : Boolean
)

interface MessageRepository {
    fun getMessages() : List<Message>
}