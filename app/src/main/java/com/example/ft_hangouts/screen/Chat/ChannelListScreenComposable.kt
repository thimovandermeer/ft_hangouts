package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.ViewModels.ChannelListViewModel

@Composable
fun ChannelListScreen(navController: NavController, viewModel: ChannelListViewModel = hiltViewModel()) {
    val chats = viewModel.getChannels()
    Log.d("ChannelListComposable, " , "channels = $chats")

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routes.AddChannel.route) },
            ) {
            }
        }
    ) {
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
        // Screen content
    }

}

