package com.example.ft_hangouts.screen.Login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.ViewModels.LoginViewModel

@Composable
fun LoginComposable(
    navController: NavHostController,
    loginstate: MutableState<LoginViewModel.LoginState>,
    password: MutableState<TextFieldValue>,
    viewModel: LoginViewModel,
    username: MutableState<TextFieldValue>
) {
    Spacer(modifier = Modifier.height(20.dp))
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            onClick = {loginstate.value = viewModel.handleLogin(username.value.toString(), password.value.toString())},
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Login")
        }
    }
    if (loginstate.value == LoginViewModel.LoginState.REDIRECT) {
        navController.navigate(Routes.Chat.route)
        loginstate.value = LoginViewModel.LoginState.DONE
    }
}