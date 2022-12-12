package com.example.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*

import org.springframework.data.annotation.Id

import org.springframework.jdbc.core.query

@Service
class MessageService(val db: JdbcTemplate) {

    fun findMessages(): List<Message> = db.query("select * from messages_full") { response, _ ->
        Message(
            response.getString("id"),
            response.getString("sender"),
            response.getString("receiver"),
            response.getString("text"),
            response.getString("isMine")
        )
    }

    fun findMessageById(id: String): List<Message> = db.query("select * from messages_full where id = ?", id) { response, _ ->
        Message(
            response.getString("id"),
            response.getString("sender"),
            response.getString("receiver"),
            response.getString("text"),
            response.getString("isMine")
        )
    }

    fun save(message: Message) {
        val id = message.messageId ?: UUID.randomUUID().toString()

        db.update("insert into messages_full values ( ?, ?, ?, ?,? )",
            id,
            message.sender,
            message.receiver,
            message.text,
            message.isMine)
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

    @GetMapping("/{id}")
    fun index(@PathVariable id: String): List<Message> =
        service.findMessageById(id)

    @PostMapping("/")
    fun post(@RequestBody message: Message) {
        service.save(message)
    }
}


data class Message(@Id var messageId: String?, val sender: String, val receiver: String, val text: String, val isMine: String)
