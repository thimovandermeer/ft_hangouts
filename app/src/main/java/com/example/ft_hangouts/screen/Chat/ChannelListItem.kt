package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ft_hangouts.repositories.Chat.Chats

@Composable
fun ChannelListItem(
    chats: Chats,
    navController: NavController
) {
    val TAG = "ChannelListItem"
    val onClickChats = {navController.navigate("MessageList/${chats.channelID}")}
    val onClickDetail = {navController.navigate("Details/${chats.channelID}")}
    Row( // 1
        modifier = Modifier

            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(start = 8.dp)) { // 3
            Text(
                text = chats.first_person.toString(),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 18.sp,
            )

        }

        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Chat",
        modifier = Modifier
            .clickable { onClickChats() }
            .size(ButtonDefaults.IconSize)
            .weight(1f))

        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Person details",
            modifier = Modifier
                .clickable { onClickDetail() }
                .size(ButtonDefaults.IconSize)
                .weight(1f))
    }
}