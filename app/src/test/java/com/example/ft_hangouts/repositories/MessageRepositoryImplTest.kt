package com.example.ft_hangouts.repositories

import com.example.ft_hangouts.repositories.Chat.*
import org.junit.Assert.assertEquals
import org.junit.Test

internal class MessageRepositoryImplTest {
    private val ChannelRepository = ChannelRepositoryImpl()
    private val MessageRepositoryImpl = MessageRepositoryImpl(ChannelRepository)

    @Test
    fun create_message_object_test() {
        var res : Message = Message("", "", "", "", false)
        val channelId = "0"
        val message = "Dahaaag"
        val currentPerson = "Thimo"
        val chatInfo = Chats("0", "Thimo", "Jonas", "Jonas")
        res = MessageRepositoryImpl.create_message_object(channelId, message,chatInfo, currentPerson)

        assertEquals("Sender is set wrongly",  "Thimo", res.sender,)
        assertEquals("Receiver is set wrongly", "Jonas", res.receiver)
        assertEquals("Text is set wrongly", "Dahaaag", res.text)
        assertEquals("Ismine is set wrongly", true, res.isMine)




    }
}