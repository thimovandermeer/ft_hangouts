package com.example.ft_hangouts.repositories.Chat

import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
) : ChannelRepository
{
    // fake data because we still need to setup the entire API structure and backend
    private val _chatsList : MutableList<Chats> = arrayListOf()
    private val TAG = "ChannelRepositoryImpl"

    init{
        fill_list()
    }
    private fun fill_list() {
        var i = 0

        while(i < 10) {
            var chats : Chats = Chats(i.toString(), "Mohammed$i")
            _chatsList.add(chats)
            i++
        }
    }
    override fun getChats() : List<Chats> {
        return _chatsList
    }
}