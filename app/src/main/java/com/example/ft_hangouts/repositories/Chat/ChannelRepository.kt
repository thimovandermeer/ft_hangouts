package com.example.ft_hangouts.repositories.Chat

import androidx.room.Entity

@Entity
data class Chats(
    val uid: String,
    val Name: String?,
)

interface ChannelRepository {
    fun getChats() : List<Chats>

}