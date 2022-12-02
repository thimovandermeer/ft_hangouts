package com.example.ft_hangouts.ViewModels

import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.repositories.Chat.Message
import com.example.ft_hangouts.repositories.Chat.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val MessageRepository: MessageRepository
) : ViewModel(){
    val TAG = "MessageListViewModel"
    fun getMessages() : List<Message>{
        return MessageRepository.getMessages()
    }
//    Log.d(TAG, "Entering Message List View Model")

}