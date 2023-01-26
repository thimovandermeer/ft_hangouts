package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.networklayer.ChannelApi
import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import com.example.ft_hangouts.repositories.Chat.Chats
import com.example.ft_hangouts.repositories.User.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PartnerInfo(
    var firstName: String = "",
    var lastName: String = "",
    var profession: String = "",
    var favoriteAnimal: String = "",
    var epicBeer: String = ""
)

enum class AddChannelApiStatus {ADDING, ERROR, DONE, INPROGRESS}
@HiltViewModel
class AddChannelViewModel @Inject constructor(
    private val ChannelRepository: ChannelRepository
) : ViewModel() {

    private val TAG = "AddChannelViewModel"
    private val _uiState = MutableStateFlow(PartnerInfo())
    val uiState : StateFlow<PartnerInfo> = _uiState.asStateFlow()

    suspend fun addChat(channelName: String) : AddChannelApiStatus {
            return ChannelRepository.addChat(channelName)
    }

    suspend fun savePersonInfo(partnerInfo: PartnerInfo) {
        return ChannelRepository.savePersonInfo(partnerInfo)
    }

    suspend fun editPersonInfo(
        person: String,
        firstName: String,
        lastName: String,
        profession: String,
        favoritebeer: String,
        favoriteAnimal: String) {
        val newInfo: PartnerInfo = PartnerInfo(
            firstName,
            lastName,
            profession,
            favoritebeer,
            favoriteAnimal
            )
        Log.d(TAG, "What is the person here $person")
        return ChannelRepository.editPersonInfo(newInfo, person)
    }

    fun createPersonInfo() {

    }

}