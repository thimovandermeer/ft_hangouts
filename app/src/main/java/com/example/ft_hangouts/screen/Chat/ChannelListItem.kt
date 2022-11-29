package com.example.ft_hangouts.screen.Chat

import android.view.View.OnClickListener
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ft_hangouts.repositories.Chat.Chats

@Composable
fun ChannelListItem(
    chats: Chats,
    onClick: (chat: Chats) -> Unit,
) {
    Row( // 1
        modifier = Modifier
            .clickable { onClick(chats) }
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(start = 8.dp)) { // 3
            Text(
                text = chats.Name.toString(),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 18.sp,
            )

        }
    }
}