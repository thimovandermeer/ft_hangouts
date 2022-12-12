package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ft_hangouts.networklayer.MessageApi
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

    init {
        getMessagesFromApi()
    }
    fun getMessagesFromApi() {
        viewModelScope.launch {
            _status.value = MessagesApiStatus.LOADING
            try {
                _messages.value = MessageApi.retrofitService.getMessage()
                Log.d(TAG, "Wat krijgen we binnen ${MessageApi.retrofitService.getMessage()}")
                _status.value = MessagesApiStatus.DONE
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "exception with reason ${e.message}")
                _status.value = MessagesApiStatus.ERROR
                _messages.value = listOf()
            }
        }
    }
//    Log.d(TAG, "Entering Message List View Model")

}