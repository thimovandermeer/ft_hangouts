package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.ViewModels.ChannelApiStatus
import com.example.ft_hangouts.ViewModels.ChannelListViewModel

@Composable
fun ChannelListScreen(
    navController: NavController,
    viewModel: ChannelListViewModel = hiltViewModel()
) {
    if (!viewModel.loaded) {
        viewModel.getChannels()
    }
    
    var chats = viewModel.channels.value
    Log.d("ChannelListComposable, " , "channels = ${chats}")
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.AddChannel.route) },
            ) {
            }
        }
    )
    {
        Box( // 3
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Log.d("ChannelListScreenComp", "wat is de status ${viewModel.status.value}")
            if(viewModel.status.value == ChannelApiStatus.LOADING) {
                Text(text = "Channel API IS LOADING")
            } else if (viewModel.status.value == ChannelApiStatus.ERROR) {
                Text(text = "Channel API IN ERROR STATE", color = Color.Red)
            }
            LazyColumn(
                Modifier.fillMaxSize()
            ) {
                if(viewModel.status.value == ChannelApiStatus.DONE) {
                    if (chats != null) {
                        items(chats) { chat ->
                            ChannelListItem(
                                chats = chat,
                                navController
                            )
                            Divider()
                        }
                    }
                }
            }
        }
    }
}


