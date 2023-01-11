package com.example.ft_hangouts.repositories.Chat

import android.nfc.Tag
import android.util.Log
import com.example.ft_hangouts.MainActivity
import com.example.ft_hangouts.ViewModels.AddChannelApiStatus
import com.example.ft_hangouts.networklayer.MessageApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val ChannelRepository: ChannelRepository
) : MessageRepository
{

    private val TAG = "MessageRepositoryImpl"
    override suspend fun getMessages(channelID: String): List<Message> {
       return  MessageApi.retrofitService.getChannelMessages(channelID)
    }

    suspend fun retrieve_channel_info(chatId: String) : Chats {
        return ChannelRepository.getChat(chatId)
    }

    fun create_message_object(chatId: String, message: String, chatInfo: Chats, current_person: String) : Message {

        val new_message : Message = Message(chatId, "", "", message, true)
        if (current_person == chatInfo.first_person) {
            new_message.sender = chatInfo.first_person
            new_message.receiver = chatInfo.second_person
        } else {
            new_message.sender = chatInfo.second_person
            new_message.receiver = chatInfo.first_person
        }

        return new_message
    }
    override suspend fun sendMessages(message: String, chatId: String) {
        val chatInfo = retrieve_channel_info(chatId)
        Log.d(TAG, "Is de message echt leeg: $message")
        val message = create_message_object(chatId, message, chatInfo, MainActivity.username)
        // coroutine scope omheen bouwen
        try {
            Log.d(TAG, "Trying to send message: ${message}\n on channelID ${chatId}")
            MessageApi.retrofitService.sendMessage(chatId, message)
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Exception send message ${e.message}")
        }
    }
}