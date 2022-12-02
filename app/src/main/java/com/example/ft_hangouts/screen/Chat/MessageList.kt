package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ft_hangouts.ViewModels.MessageListViewModel

@Composable
fun MessageList(
    navController: NavController,
    uuid: String?,
    modifier: Modifier = Modifier,
    viewModel: MessageListViewModel

) {
    val messages = viewModel.getMessages()
    Log.d("MessageList", "Messages ${messages}")
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            reverseLayout = true, // 5
        ) {
            items(messages) { message ->
                MessageCard(message) // 6
            }
        }
    }
}