package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.networklayer.ChannelApi
import com.example.ft_hangouts.repositories.Chat.Chats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class AddChannelApiStatus {ADDING, ERROR, DONE, INPROGRESS}
@HiltViewModel
class AddChannelViewModel @Inject constructor(

) : ViewModel() {

    private val TAG = "AddChannelViewModel"
    private val _status = MutableLiveData<AddChannelApiStatus>()
    val status: LiveData<AddChannelApiStatus> = _status

    suspend fun addChat(channelName: String) : AddChannelApiStatus {
        Log.d(TAG, "Creating chat with name ${channelName}")
        val newChat = Chats("0", channelName.toString())
        try {
            Log.d(TAG, "wat zenden we die kant uit ${newChat}")
            ChannelApi.retrofitService.CreateChannel(newChat)
            return AddChannelApiStatus.DONE
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Exception add chat ${e.message}")
            return AddChannelApiStatus.ERROR
        }
    }
}