package com.example.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*

import org.springframework.data.annotation.Id
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType


@Service
class MessageService() {
    var _messages: MutableList<Message> = mutableListOf()
    var _channels: MutableList<Chats> = mutableListOf()
    var _persons: MutableList<PartnerInfo> = mutableListOf()
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

    fun findChannels(personID: String): List<Chats> {
        val res = _channels.filter { chats ->  chats.first_person == personID || chats.second_person == personID}
        return res
    }

    fun saveChannel(newChannelName : Chats) {
        println("Channel = ${newChannelName}")
        println("Is dit wel wat ik wil: ${newChannelName.first_person}")
        // create a check if something already exists
        println("Waarvoor moet ik nu checken $_channels")
        if (!_channels.any { chats -> chats.first_person == newChannelName.first_person ||  chats.second_person == newChannelName.first_person}) {
            println("Ik kom in de if")
            var newChat = Chats(UUID.randomUUID().toString(), newChannelName.first_person, newChannelName.second_person, newChannelName.creator)
            _channels.add(newChat)
        }
    }

    fun getChannel(ChannelID: String) : Chats {
        println("Searching channelID ${ChannelID}")
        println("Channels = ${_channels}")
        val res = _channels.filter { chats ->  chats.channelID == ChannelID}
        return res[0]
    }

    fun savePerson(personInfo: PartnerInfo) {
        println("adding personinfo ${personInfo}")
        _persons.add(personInfo)
    }

    fun getPerson(person: String) : PartnerInfo {
        println("Seaching person info with id ${person}")
        println("person array = $_persons")
        val res = _persons.filter { partnerInfo ->  partnerInfo.firstName == person}
        println("found person ${res}")
        return if (res.isNotEmpty())
            res[0]
        else
            PartnerInfo("", "", "", "", "")
    }

    fun changeUsersInChat(oldPerson : String, newPerson: String) {
        println("old channels list ${_channels}")
        _channels.filter { Chats -> Chats.first_person == oldPerson}.forEach { Chats -> Chats.first_person = newPerson }
        _channels.filter { Chats -> Chats.second_person == oldPerson}.forEach { Chats -> Chats.second_person = newPerson }
        println("new channels list ${_channels}")
    }

    fun editPerson(person: String, PersonInfo: PartnerInfo) {
        var newInfo = PartnerInfo("", "", "", "", "")
        val toChange_res = _persons.filter { it.firstName == person}
        val toChange = toChange_res[0]
        if (PersonInfo.firstName == "") {
            newInfo.firstName = toChange.firstName
        } else {
            newInfo.firstName = PersonInfo.firstName
        }
        if (PersonInfo.lastName == "") {
            newInfo.lastName = toChange.lastName
        } else {
            newInfo.lastName = PersonInfo.lastName
        }

        if (PersonInfo.profession == "") {
            newInfo.profession = toChange.profession
        } else {
            newInfo.profession = PersonInfo.profession
        }
        if(PersonInfo.epicBeer == "") {
            newInfo.epicBeer = toChange.epicBeer
        } else {
            newInfo.epicBeer = PersonInfo.epicBeer
        }

        if(PersonInfo.favoriteAnimal == "") {
            newInfo.favoriteAnimal = toChange.favoriteAnimal
        } else {
            newInfo.favoriteAnimal = PersonInfo.favoriteAnimal
        }
        _persons.remove(toChange)
        _persons.add(newInfo)
        changeUsersInChat(toChange.firstName, newInfo.firstName)
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


        @PostMapping("/{channelID}")
        fun post(@PathVariable channelID: String, @RequestBody message: Message) {
            service.save(message)
        }

        @GetMapping("/channels/searchall/{personID}")
        fun indexChannels(@PathVariable personID : String) : List<Chats> =
            service.findChannels(personID)

        @GetMapping("/channels/{channelID}")
        fun getChannelInfo(@PathVariable channelID: String) : Chats =
            service.getChannel(channelID)
        @PostMapping("/channels")
        fun postChannel(@RequestBody channelName: Chats) {
            service.saveChannel(channelName)
        }

        @PostMapping("/person")
        fun addPerson(@RequestBody personInfo: PartnerInfo) {
            service.savePerson(personInfo)
        }

        @GetMapping("/person/{person}")
        fun getPersonInfo(@PathVariable person: String) : PartnerInfo =
            service.getPerson(person)

        @PostMapping("/person/{person}")
        fun editPersonInfo(@PathVariable person:String, @RequestBody personInfo: PartnerInfo) =
            service.editPerson(person, personInfo)
    }



data class Message(@Id var messageId: String?, var channelID: String, val sender: String, val receiver: String, val isMine: Boolean, val text: String)
data class Chats(@Id var channelID: String, var first_person: String, var second_person: String, var creator: String)

data class PartnerInfo(
    var firstName: String,
    var lastName: String,
    var profession: String,
    var favoriteAnimal: String,
    var epicBeer: String
)