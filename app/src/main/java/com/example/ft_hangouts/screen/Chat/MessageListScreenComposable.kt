package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MessageListScreen(navController: NavController, uuid: String?) {
    Text(text = "halloooooo")
    Log.d("MessageListScreen", "Which pane do I enter ${uuid}")

}