package com.example.ft_hangouts.repositories.Chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Channel(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "ChannelName") val Name: String?,
//    @ColumnInfo(name = "") val password: String?,
//    @ColumnInfo(name = "Email") val email: String?
)
interface ChannelRepository {
    fun getChannels() : List<Channel>

}