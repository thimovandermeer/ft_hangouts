package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.*
import com.example.ft_hangouts.networklayer.MessageApi
import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import com.example.ft_hangouts.repositories.Chat.Message
import com.example.ft_hangouts.repositories.Chat.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MessagesApiStatus {LOADING, ERROR, DONE}

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val MessageRepository: MessageRepository
) : ViewModel(){
    val TAG = "MessageListViewModel"
    private val _status = MutableLiveData<MessagesApiStatus>()
    val status: LiveData<MessagesApiStatus> = _status

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages
    var channelID = MutableLiveData<String>()
    var loaded : Boolean = false

    // dit moet naar de repository impl
    fun getMessages() {
        viewModelScope.launch {
            _status.value = MessagesApiStatus.LOADING
            try {
                _messages.value = MessageRepository.getMessages(channelID.value.toString())
                _status.value = MessagesApiStatus.DONE
                loaded = true
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "exception with reason ${e.message}")
                _status.value = MessagesApiStatus.ERROR
                _messages.value = listOf()
            }
        }
    }

//    Log.d(TAG, "Entering Message List View Model")

}