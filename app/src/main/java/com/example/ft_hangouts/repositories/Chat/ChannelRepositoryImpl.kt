package com.example.ft_hangouts.repositories.Chat

import android.util.Log
import com.example.ft_hangouts.MainActivity
import com.example.ft_hangouts.ViewModels.AddChannelApiStatus
import com.example.ft_hangouts.ViewModels.PartnerInfo
import com.example.ft_hangouts.networklayer.ChannelApi
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
) : ChannelRepository
{
    // fake data because we still need to setup the entire API structure and backend
    private var _chatsList : MutableList<Chats> = mutableListOf()
    private val TAG = "ChannelRepositoryImpl"
    private var _uuid = 0;

    override suspend fun getChat(chatId: String) : Chats {
        return ChannelApi.retrofitService.getChannel(chatId)
    }
    override suspend fun getChats(): List<Chats> {
        Log.d(TAG, "currentusername ${MainActivity.username}")
        val res = ChannelApi.retrofitService.getChannels(MainActivity.username)
        Log.d(TAG, "Res = $res")
        return res
    }
    override suspend fun addChat(chatPartner: String) : AddChannelApiStatus {
        Log.d(TAG, "Creating chat with name ${chatPartner}")
        val newChat = Chats("0", chatPartner.toString(), MainActivity.username, MainActivity.username)
        try {
            ChannelApi.retrofitService.CreateChannel(newChat)
            return AddChannelApiStatus.DONE
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Exception add chat ${e.message}")
            return AddChannelApiStatus.ERROR
        }
    }

    override suspend fun savePersonInfo(partnerInfo: PartnerInfo) {
        Log.d(TAG, "Saving person info ${partnerInfo}")
        try {
            ChannelApi.retrofitService.SavePersonInfo(partnerInfo)
        } catch (e: java.lang.Exception) {
            Log.d(TAG,"Exception occured ${e.message}")
        }
    }

    override suspend fun getPersonInfo(person: String): PartnerInfo {
        Log.d(TAG, "Retrieve person info with ID ${person}")
        return ChannelApi.retrofitService.getPersonInfo(person)
    }

    override suspend fun editPersonInfo(partnerInfo: PartnerInfo, person: String) {
        return ChannelApi.retrofitService.editPersonInfo(person, partnerInfo)
    }

}