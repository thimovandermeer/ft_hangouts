package com.example.ft_hangouts.screen.SignUp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.RegisterViewModel

@Composable
fun Passwordcomposable(
    accept: MutableState<RegisterViewModel.state>,
    password: MutableState<TextFieldValue>
) {
    if (accept.value == RegisterViewModel.state.PASSWORDINVALID) {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password invalid", color = Color.Red) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })
    } else if (accept.value == RegisterViewModel.state.PASSWORDEXISTS) {
        TextField(
            label = { Text(text = "Password already exists", color = Color.Red) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })
    }else {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })
    }
}