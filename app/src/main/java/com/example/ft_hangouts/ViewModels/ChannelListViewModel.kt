package com.example.ft_hangouts.ViewModels

import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.repositories.Chat.Chats
import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChannelListViewModel @Inject constructor(
    private val ChannelRepository: ChannelRepository
) : ViewModel() {
    val chatsList : List<Chats> = emptyList()

    fun getChannels() : List<Chats> {
        return ChannelRepository.getChats()
    }

    fun addChat() {
        return ChannelRepository.addChat()
    }
}