package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.ViewModels.MessageListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MessageListScreen(navController: NavController, uuid: String?, viewModel: MessageListViewModel = hiltViewModel()) {
    Log.d("MessageListScreen", "Which pane do I enter ${uuid}")
    MessageList(
        navController = navController,
        uuid = uuid,
        viewModel = viewModel
    )
}