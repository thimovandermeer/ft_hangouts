package com.example.ft_hangouts.screen.Chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ViewModels.MessageInputViewModel

@Composable
fun MessageInput(
    uuid: String?,
    messageInputViewModel: MessageInputViewModel = hiltViewModel(), // 1
) {
    messageInputViewModel.channelID.value = uuid
    var inputValue by remember { mutableStateOf("") } // 2

    fun sendMessage() { // 3
        messageInputViewModel.sendMessage(inputValue)
        inputValue = ""
    }

    Row {
        TextField( // 4
            modifier = Modifier.weight(1f),
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Send),
            keyboardActions = KeyboardActions { sendMessage() },
        )
        Button( // 5
            modifier = Modifier.height(56.dp),
            onClick = { sendMessage() },
            enabled = inputValue.isNotBlank(),
        ) {
            Icon( // 6
                imageVector = Icons.Default.Send,
                contentDescription = stringResource(androidx.compose.ui.R.string.close_drawer)
            )
        }
    }
}