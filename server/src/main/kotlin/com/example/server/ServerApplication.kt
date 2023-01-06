package com.example.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*

import org.springframework.data.annotation.Id


@Service
class MessageService() {
    var _messages: MutableList<Message> = mutableListOf()
    var _channels: MutableList<Chats> = mutableListOf()
    fun findMessages(): List<Message> {
        println("All messages in return functin= ${_messages}")
        return _messages
    }

    fun findbyChannelName(channelId: String): List<Message> {
        var channelres = _messages.filter { message: Message -> message.channelID.equals(channelId) }
        return channelres
    }

    fun save(message: Message) {
        println("Message = ${message}")
        println("All messages = ${_messages}")
        val id = message.messageId ?: UUID.randomUUID().toString()
        message.messageId = id
        _messages.add(message)
    }

    fun findChannels(): MutableList<Chats> {
        return _channels
    }

    fun saveChannel(newChannelName : Chats) {
        println("Channel = ${newChannelName}")
        println("Is dit wel wat ik wil: ${newChannelName.channelName}")
        // create a check if something already exists
        var newChat = Chats(UUID.randomUUID().toString(), newChannelName.channelName)
        _channels.add(newChat)
    }

}
    @SpringBootApplication
    class ServerApplication

    fun main(args: Array<String>) {
        runApplication<ServerApplication>(*args)
    }

    @RestController
    class MessageController(val service: MessageService) {
        @GetMapping("/messages")
        fun index(): List<Message> = service.findMessages()

        @GetMapping("/{channelID}")
        fun index(@PathVariable channelID: String) : List<Message?> =
            service.findbyChannelName(channelID)


        @PostMapping("/")
        fun post(@RequestBody message: Message) {
            service.save(message)
        }

        @GetMapping("/channels")
        fun indexChannels() : MutableList<Chats> =
            service.findChannels()

        @PostMapping("/channels")
        fun postChannel(@RequestBody channelName: Chats) {
            service.saveChannel(channelName)
        }
    }



data class Message(@Id var messageId: String?, var channelID: String, val sender: String, val receiver: String, val isMine: Boolean, val text: String)
data class Chats(@Id var channelID: String, var channelName: String)