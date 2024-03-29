package com.example.ft_hangouts.repositories.Chat

import com.example.ft_hangouts.ViewModels.AddChannelApiStatus
import com.example.ft_hangouts.ViewModels.PartnerInfo


data class Chats(
    val channelID: String,
    val first_person: String,
    val second_person: String,
    val creator: String
)

interface ChannelRepository {
    suspend fun getChats(): List<Chats>
    suspend fun getChat(chatId: String): Chats
    suspend fun addChat(channelName: String) : AddChannelApiStatus
    suspend fun savePersonInfo(partnerInfo: PartnerInfo)
    suspend fun getPersonInfo(person: String) : PartnerInfo
    suspend fun editPersonInfo(partnerInfo: PartnerInfo, person: String)
}