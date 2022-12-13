package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.repositories.Chat.MessageInputRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageInputViewModel @Inject constructor(
    private val MessageInputRepository: MessageInputRepository
)  : ViewModel(){
    val TAG = "MessageInputViewModel"
    var channelID = MutableLiveData<String>()
    fun sendMessage(inputValue: String) {
        MessageInputRepository.sendToServer(inputValue, channelID.value.toString())
        Log.d(TAG, "To save ${inputValue}")
    }
}