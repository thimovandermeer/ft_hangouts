package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ft_hangouts.networklayer.ChannelApi
import com.example.ft_hangouts.repositories.Chat.Chats
import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ChannelApiStatus {LOADING, ERROR, DONE}
@HiltViewModel
class ChannelListViewModel @Inject constructor(
    private val ChannelRepository: ChannelRepository
) : ViewModel() {
    private val TAG = "ChannelListViewModel"
    private val _status = MutableLiveData<ChannelApiStatus>()
    val status: LiveData<ChannelApiStatus> = _status

    private var _channels = MutableLiveData<List<Chats>>()
    val channels: LiveData<List<Chats>> = _channels
    var loaded: Boolean = false
    // dit moet naar de repository verplaats worden
    fun getChannels() {
        viewModelScope.launch {
            _status.value = ChannelApiStatus.LOADING
            try {
                _channels.value = ChannelRepository.getChats()
                _status.value = ChannelApiStatus.DONE
                loaded = true
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "ChannelListViewModel Exception ${e.message}")
                _status.value = ChannelApiStatus.ERROR
                _channels.value = listOf()
            }
        }
    }
}