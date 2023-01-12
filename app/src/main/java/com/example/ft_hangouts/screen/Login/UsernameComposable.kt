package com.example.ft_hangouts.screen.Login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ViewModels.LoginViewModel

@Composable
fun UsernameComposable(
    username: MutableState<TextFieldValue>,
    loginstate: MutableState<LoginViewModel.LoginState>
) {
    Spacer(modifier = Modifier.height(20.dp))
    if (loginstate.value == LoginViewModel.LoginState.USERNOEXIST) {
        TextField(
            label = { Text(text = stringResource(R.string.userdoesnotexitsText), color = Color.Red) },
            value = username.value,
            onValueChange = { username.value = it })
    }else {
        TextField(
            label = { Text(text = stringResource(R.string.UsernameText)) },
            value = username.value,
            onValueChange = { username.value = it })
    }

}