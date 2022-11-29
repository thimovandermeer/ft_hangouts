package com.example.ft_hangouts.repositories.Chat

import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
) : ChannelRepository
{
    // fake data because we still need to setup the entire API structure and backend
    private val _channelList : MutableList<Channel> = arrayListOf()
    private val TAG = "ChannelRepositoryImpl"

    init{
        fill_list()
    }
    private fun fill_list() {
        var i = 0

        while(i < 10) {
            var channel : Channel = Channel(i.toString(), "Channel$i")
            _channelList.add(channel)
            i++
        }
    }
    override fun getChannels() : List<Channel> {
        return _channelList
    }
}