package com.example.ft_hangouts.repositories.Chat

interface MessageInputRepository {
    fun sendToServer(input: String, toString: String)
}