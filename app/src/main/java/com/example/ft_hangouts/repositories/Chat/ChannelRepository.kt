package com.example.ft_hangouts.repositories.Chat

import com.example.ft_hangouts.ViewModels.AddChannelApiStatus


data class Chats(
    val channelID: String,
    val channelName: String,
)

interface ChannelRepository {
    suspend fun getChats(): List<Chats>
    suspend fun addChat(channelName: String) : AddChannelApiStatus

}