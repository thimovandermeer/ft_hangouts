package com.example.ft_hangouts.repositories.Chat

import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
) : MessageRepository
{
    var _messages: MutableList<Message> = arrayListOf()

    init {
        createFakeMessages()
    }
    private fun createFakeMessages() {
        var i = 0
        var isMine = false
        while (i != 10) {
            isMine = i % 2 == 0
            var Message : Message = Message("1", "Mohammed1", "Mohammed2", "Je moeder is tantoe dik a ouleh", isMine)
            _messages.add(Message)
            i++
        }
    }
    override fun getMessages(): List<Message> {
        return _messages
    }
}