package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.ViewModels.ChannelListViewModel

@Composable
fun ChannelListScreen(navController: NavController, viewModel: ChannelListViewModel = hiltViewModel()) {
    val chats = viewModel.getChannels()
    Log.d("ChannelListComposable, " , "channels = $chats")
    Box( // 3
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(Modifier.fillMaxSize()) { // 5
            items(chats) { chat ->
                ChannelListItem(
                    chats = chat,
                    onClick = {navController.navigate("MessageList/${chat.uid}")},
                )
                Divider()
            }
        }
    }
}

