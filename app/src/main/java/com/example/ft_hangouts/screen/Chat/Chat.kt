package com.example.ft_hangouts.screen.Chat

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ChatPage(navController: NavHostController) {
    ChannelListScreen(navController)
}