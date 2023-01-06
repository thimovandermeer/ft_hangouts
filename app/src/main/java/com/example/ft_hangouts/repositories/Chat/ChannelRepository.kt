package com.example.ft_hangouts.repositories.Chat

import com.example.ft_hangouts.ViewModels.AddChannelApiStatus


data class Chats(
    val channelID: String,
    val first_person: String,
    val second_person: String,
    val creator: String
)

interface ChannelRepository {
    suspend fun getChats(): List<Chats>
    suspend fun addChat(channelName: String) : AddChannelApiStatus

}