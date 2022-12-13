package com.example.ft_hangouts.repositories.Chat


data class Chats(
    val uid: String,
    val Name: String?,
)

interface ChannelRepository {
    fun getChats() : List<Chats>
    fun addChat()

}