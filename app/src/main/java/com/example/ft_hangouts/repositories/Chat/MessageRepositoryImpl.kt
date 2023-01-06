package com.example.ft_hangouts.repositories.Chat

import com.example.ft_hangouts.networklayer.MessageApi
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
) : MessageRepository
{


    override suspend fun getMessages(channelID: String): List<Message> {
       return  MessageApi.retrofitService.getChannelMessages(channelID)
    }

}