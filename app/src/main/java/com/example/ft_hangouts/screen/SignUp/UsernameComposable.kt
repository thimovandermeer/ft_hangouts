package com.example.ft_hangouts.screen.SignUp

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.ft_hangouts.R
import com.example.ft_hangouts.RegisterViewModel

@Composable
fun Usernamecomposeable(accept: MutableState<RegisterViewModel.RegisterState>, username: MutableState<TextFieldValue>) {
    if (accept.value == RegisterViewModel.RegisterState.USERNAMEINVALID) {
        TextField(

            label = { Text(text = stringResource(R.string.usernameinvalidText), color = Color.Red) },
            value = username.value,
            onValueChange = { username.value = it })
    } else if (accept.value == RegisterViewModel.RegisterState.USERALREADYEXISTS) {
        TextField(
            label = { Text(text = stringResource(R.string.usernamealreadyexistsText), color = Color.Red) },
            value = username.value,
            onValueChange = { username.value = it })
    } else {
        TextField(

            label = { Text(text = stringResource(R.string.usernameText)) },
            value = username.value,
            onValueChange = { username.value = it })
    }
}