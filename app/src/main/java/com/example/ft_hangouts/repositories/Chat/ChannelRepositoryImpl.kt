package com.example.ft_hangouts.repositories.Chat

import android.util.Log
import com.example.ft_hangouts.networklayer.ChannelApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
) : ChannelRepository
{
    // fake data because we still need to setup the entire API structure and backend
    private var _chatsList : MutableList<Chats> = mutableListOf()
    private val TAG = "ChannelRepositoryImpl"
    private var _uuid = 0;

    init {
        Log.d(TAG, "Retrieving list of chats")
        updateChats()
    }

    fun updateChats(){
        Log.d(TAG, "Kom ik hier nog?")
        CoroutineScope(Dispatchers.IO).launch {
            _chatsList = ChannelApi.retrofitService.getChannels() as MutableList<Chats>
        }
    }

    override fun getChats(): List<Chats> {
        return _chatsList
    }

    private fun check_exists(chatname: String) : Boolean {
        Log.d(TAG, "Chats is check exists ${_chatsList}")
        if (_chatsList.find { chat ->  chat.channelName == chatname} == null)
            return false
        return true
    }

    private fun incrementUid() {
        Log.d(TAG, "New uuid is $_uuid")
        _uuid += 1
    }

    private fun sendApiRequest(newChat: Chats) {
        CoroutineScope(Dispatchers.IO).launch {
            ChannelApi.retrofitService.CreateChannel(newChat)
        }
    }
    override fun addChat(chatname: String) : Boolean {
        if (check_exists(chatname)) {
            Log.d(TAG,"Check exists returns true")
            return false
        }
        else {
            val newChat : Chats = Chats(_uuid.toString(), chatname)
            Log.d(TAG, "New chat being added to list ${newChat}")
            sendApiRequest(newChat)
            incrementUid()
            updateChats()
            Log.d(TAG, "Chat list after addition ${_chatsList}")
            return true
        }
    }
}