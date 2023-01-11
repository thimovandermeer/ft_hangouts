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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ft_hangouts.ViewModels.MessageListViewModel
import kotlinx.coroutines.launch

@Composable
fun MessageInput(
    uuid: String?,
    messageInputViewModel: MessageListViewModel = hiltViewModel(), // 1
) {
    var inputValue by remember { mutableStateOf("") } // 2
    val coroutineScope = rememberCoroutineScope()
    fun sendMessage() { // 3
        if (uuid != null) {
            coroutineScope.launch {
                messageInputViewModel.sendMessage(inputValue.toString(), uuid)

            }
        }
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