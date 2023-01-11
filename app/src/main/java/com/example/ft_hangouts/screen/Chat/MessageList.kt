package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.ft_hangouts.ViewModels.MessageListViewModel
import com.example.ft_hangouts.ViewModels.MessagesApiStatus
import com.example.ft_hangouts.repositories.Chat.Chats
import com.example.ft_hangouts.repositories.Chat.Message


@Composable
fun MessageList(
    navController: NavController,
    uuid: String?,
    modifier: Modifier = Modifier,
    viewModel: MessageListViewModel,

) {
    viewModel.channelID.value = uuid
    val messageListUiState by viewModel.uiState.collectAsState()
    val messages = messageListUiState.currentListOfMessages
    if(viewModel.channelID.value != null && !viewModel.loaded) {
        viewModel.getMessages()
    }

    Log.d("MessageList", "Wordt deze nou opnieuw aangeroepen of niet ${messages}")
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        
        if(viewModel.status.value == MessagesApiStatus.LOADING) {
            Text(text = "MESSAGE API IS LOADING")
        } else if (viewModel.status.value == MessagesApiStatus.ERROR) {
            Text(text = "MESSAGE API IN ERROR STATE", color = Color.Red)
        } else {
            Text(text = "Message API IN DONE", color = Color.Green)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            reverseLayout = true, // 5
        ) {
            Log.d("MessageList", "voor items ${messages}")
            items(messages) { message ->
                MessageCard(message) // 6
            }
        }
    }
}
