package com.example.ft_hangouts.ViewModels

import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.repositories.Chat.Channel
import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChannelListViewModel @Inject constructor(
    private val ChannelRepository: ChannelRepository
) : ViewModel() {
    val channelList : List<Channel> = emptyList()

    fun getChannels() : List<Channel> {
        return ChannelRepository.getChannels()
    }
}