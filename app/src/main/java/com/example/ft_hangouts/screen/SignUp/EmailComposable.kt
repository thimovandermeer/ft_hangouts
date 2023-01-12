package com.example.ft_hangouts.screen.SignUp

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
import com.example.ft_hangouts.RegisterViewModel

@Composable
fun Emailcomposable(
    accept: MutableState<RegisterViewModel.RegisterState>,
    email: MutableState<TextFieldValue>
) {
    val TAG = "Email composable"
    if (accept.value == RegisterViewModel.RegisterState.EMAILINVALID) {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(R.string.emailincorrectText), color = Color.Red) },
            value = email.value,
            onValueChange = { email.value = it })
    } else if (accept.value == RegisterViewModel.RegisterState.EMAILEXISTS) {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(R.string.emailalreadyexistsText), color = Color.Red) },
            value = email.value,
            onValueChange = { email.value = it })
    }else {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(R.string.emailText)) },
            value = email.value,
            onValueChange = { email.value = it })
    }
}