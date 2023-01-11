package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.repositories.Chat.Chats
import com.example.ft_hangouts.repositories.Chat.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageInputViewModel @Inject constructor(
    private val MessageRepository: MessageRepository
)  : ViewModel(){
    val TAG = "MessageInputViewModel"
    var channelID = MutableLiveData<String>()
    suspend fun sendMessage(inputValue: String, chatId:String) {
        MessageRepository.sendMessages(inputValue, chatId)
        Log.d(TAG, "To save ${inputValue}")
    }
}