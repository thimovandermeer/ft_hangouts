package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.*
import com.example.ft_hangouts.networklayer.MessageApi
import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import com.example.ft_hangouts.repositories.Chat.Message
import com.example.ft_hangouts.repositories.Chat.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MessagesApiStatus {LOADING, ERROR, DONE}
data class UiiState(
    var currentListOfMessages: MutableList<Message> = mutableListOf<Message>()
    )

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val MessageRepository: MessageRepository
) : ViewModel(){
    val TAG = "MessageListViewModel"
    private val _status = MutableLiveData<MessagesApiStatus>()
    val status: LiveData<MessagesApiStatus> = _status

    private var _messages: MutableList<Message> = mutableListOf()
    private val _uiState = MutableStateFlow(UiiState())
    val uiState : StateFlow<UiiState> = _uiState.asStateFlow()

    var channelID = MutableLiveData<String>()
    var loaded : Boolean = false

    // need to add this as soon as my mutable live data state works
    suspend fun sendMessage(inputValue: String, chatId:String) {
        MessageRepository.sendMessages(inputValue, chatId)
        Log.d(TAG, "To save ${inputValue}")
        getMessages()
    }

    // dit moet naar de repository impl
    fun getMessages() {
        Log.d(TAG, "Wat is de waarde van _messages voor get messages ${_uiState.value.currentListOfMessages}")
        viewModelScope.launch {
            _status.value = MessagesApiStatus.LOADING
            try {
                _messages = MessageRepository.getMessages(channelID.value.toString()) as MutableList<Message>
                _status.value = MessagesApiStatus.DONE
                _uiState.update { uiiState ->
                    uiiState.copy(
                        currentListOfMessages = _messages
                    )
                }
                loaded = true
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "exception with reason ${e.message}")
                _status.value = MessagesApiStatus.ERROR
                _messages = mutableListOf()
                _uiState.update { uiiState ->
                    uiiState.copy(
                        currentListOfMessages = _messages
                    )
                }
            }
        }
    }


}