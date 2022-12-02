package com.example.ft_hangouts.screen.Chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ft_hangouts.repositories.Chat.Message

@Composable
fun MessageCard(message: Message) { // 1
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when { // 2
            message.isMine -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(message), // 3
            backgroundColor = when {
                message.isMine -> MaterialTheme.colors.primary
                else -> MaterialTheme.colors.secondary
            },
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = message.text,
                color = when {
                    message.isMine -> MaterialTheme.colors.onPrimary
                    else -> MaterialTheme.colors.onSecondary
                },
            )
        }
        if (message.isMine) {
            Text( // 4
                text = message.sender,
                fontSize = 12.sp,
            )
        } else {
            Text( // 4
                text = message.receiver,
                fontSize = 12.sp,
            )
        }
    }
}

@Composable
fun cardShapeFor(message: Message): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when {
        message.isMine -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}