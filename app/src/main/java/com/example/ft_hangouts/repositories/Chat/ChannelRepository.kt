package com.example.ft_hangouts.repositories.Chat


data class Chats(
    val channelID: String,
    val channelName: String,
)

interface ChannelRepository {
    fun getChats() : List<Chats>
    fun addChat(chatname : String) : Boolean

}