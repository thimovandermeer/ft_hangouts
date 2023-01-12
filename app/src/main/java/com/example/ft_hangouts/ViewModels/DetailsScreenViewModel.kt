package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class State(
    var partnerInfo: PartnerInfo
)
@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val ChannelRepository: ChannelRepository
) : ViewModel() {
    val TAG = "DetailsScreenViewModel"
    private val _status = MutableLiveData<MessagesApiStatus>()
    val status: LiveData<MessagesApiStatus> = _status

    private var _details = MutableStateFlow(State(PartnerInfo()))
    val details : StateFlow<State> = _details.asStateFlow()
    var loaded : Boolean = false

    fun getDetails(person: String) {
        viewModelScope.launch {
            _status.value = MessagesApiStatus.LOADING
            try {
                Log.d(TAG, "Which name are we looking for ${person}")
                _details.update { State ->
                    State.copy(
                        partnerInfo = ChannelRepository.getPersonInfo(person)
                    )
                }
                _status.value = MessagesApiStatus.DONE
                loaded = true
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "Exception with ${e.message}")
                _status.value = MessagesApiStatus.ERROR
                _details.update {
                    state ->
                    state.copy(
                        partnerInfo = PartnerInfo()
                    )
                }
            }
        }
    }
}