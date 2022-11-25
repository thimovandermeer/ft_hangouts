package com.example.ft_hangouts.screen.SignUp

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.example.ft_hangouts.RegisterViewModel

@Composable
fun Usernamecomposeable(accept: MutableState<RegisterViewModel.state>, username: MutableState<TextFieldValue>) {
    if (accept.value == RegisterViewModel.state.USERNAMEINVALID) {
        TextField(

            label = { Text(text = "Username invalid", color = Color.Red) },
            value = username.value,
            onValueChange = { username.value = it })
    } else if (accept.value == RegisterViewModel.state.USERALREADYEXISTS) {
        TextField(
            label = { Text(text = "Username already exists", color = Color.Red) },
            value = username.value,
            onValueChange = { username.value = it })
    } else {
        TextField(

            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it })
    }
}