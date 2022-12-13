package com.example.ft_hangouts.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.networklayer.MessageApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageInputViewModel @Inject constructor(

)  : ViewModel(){
    val TAG = "MessageInputViewModel"

    fun sendMessage(inputValue: String) {
        Log.d(TAG, "To save ${inputValue}")
    }
}