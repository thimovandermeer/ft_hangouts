package com.example.ft_hangouts.screen.Chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ft_hangouts.repositories.Chat.Channel

@Composable
fun ChannelListItem(channel: Channel) {
    Row( // 1
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Avatar(channel) // 2
        Column(modifier = Modifier.padding(start = 8.dp)) { // 3
            Text(
                text = channel.Name.toString(),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 18.sp,
            )

//            val lastMessageText = channel.messages.lastOrNull()?.text ?: "..."
//            Text(
//                text = lastMessageText,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//            )
        }
    }
}