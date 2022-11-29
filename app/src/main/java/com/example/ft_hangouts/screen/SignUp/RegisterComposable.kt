package com.example.ft_hangouts.screen.SignUp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ft_hangouts.RegisterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Registercomposable(
    navController: NavHostController,
    accept: MutableState<RegisterViewModel.RegisterState>,
    viewModel: RegisterViewModel,
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    email: MutableState<TextFieldValue>
) {
    val coroutineScope = rememberCoroutineScope()
    Spacer(modifier = Modifier.height(20.dp))
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            onClick = {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        accept.value = viewModel.handleInput(username.value.toString(), email.value.toString(), password.value.toString())
                    }
                }
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Register")
        }
    }
}