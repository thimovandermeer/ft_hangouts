package com.example.ft_hangouts.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ft_hangouts.networklayer.MessageApi
import com.example.ft_hangouts.repositories.Chat.Message
import com.example.ft_hangouts.repositories.Chat.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val MessageRepository: MessageRepository
) : ViewModel(){
    val TAG = "MessageListViewModel"
    fun getMessages() : List<Message>{
        return MessageRepository.getMessages()
    }

    fun getMessagesFromApi() {
        viewModelScope.launch {
            val apiResult = MessageApi.retrofitService.getMessage()
        }
    }
//    Log.d(TAG, "Entering Message List View Model")

}