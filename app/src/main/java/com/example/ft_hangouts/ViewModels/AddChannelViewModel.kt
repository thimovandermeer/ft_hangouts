package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.networklayer.ChannelApi
import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import com.example.ft_hangouts.repositories.Chat.Chats
import com.example.ft_hangouts.repositories.User.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class AddChannelApiStatus {ADDING, ERROR, DONE, INPROGRESS}
@HiltViewModel
class AddChannelViewModel @Inject constructor(
    private val ChannelRepository: ChannelRepository
) : ViewModel() {

    private val TAG = "AddChannelViewModel"
    private val _status = MutableLiveData<AddChannelApiStatus>()
    val status: LiveData<AddChannelApiStatus> = _status

    suspend fun addChat(channelName: String) : AddChannelApiStatus {
            return ChannelRepository.addChat(channelName)
    }

}