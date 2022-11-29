package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.ViewModels.ChannelListViewModel
import com.example.ft_hangouts.ViewModels.LoginViewModel

@Composable
fun ChannelListScreen(navController: NavController, viewModel: ChannelListViewModel = hiltViewModel()) {
    val channels = viewModel.getChannels()
    Log.d("ChannelListComposable, " , "channels = $channels")
    Box( // 3
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(Modifier.fillMaxSize()) { // 5
            items(channels) { channel ->
                ChannelListItem(channel)
                Divider()
            }
        }
    }
}

