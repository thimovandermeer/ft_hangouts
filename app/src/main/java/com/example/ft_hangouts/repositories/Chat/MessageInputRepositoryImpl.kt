package com.example.ft_hangouts.repositories.Chat

import com.example.ft_hangouts.MainActivity
import javax.inject.Inject

class MessageInputRepositoryImpl @Inject constructor(
) : MessageInputRepository{

    override fun sendToServer(input: String, channelID: String) {
    var new_message : Message = Message(channelID, MainActivity.username, receiver = "Dit moet ik nog fixen", input, true)

    }
}