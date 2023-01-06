package com.example.ft_hangouts.repositories.Chat

import android.util.Log
import com.example.ft_hangouts.MainActivity
import com.example.ft_hangouts.ViewModels.AddChannelApiStatus
import com.example.ft_hangouts.networklayer.ChannelApi
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
) : ChannelRepository
{
    // fake data because we still need to setup the entire API structure and backend
    private var _chatsList : MutableList<Chats> = mutableListOf()
    private val TAG = "ChannelRepositoryImpl"
    private var _uuid = 0;

    override suspend fun getChats(): List<Chats> {
        return ChannelApi.retrofitService.getChannels()
    }
    override suspend fun addChat(chatPartner: String) : AddChannelApiStatus {
        Log.d(TAG, "Creating chat with name ${chatPartner}")
        val newChat = Chats("0", chatPartner.toString(), MainActivity.username, MainActivity.username)
        try {
            ChannelApi.retrofitService.CreateChannel(newChat)
            return AddChannelApiStatus.DONE
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Exception add chat ${e.message}")
            return AddChannelApiStatus.ERROR
        }
    }

}