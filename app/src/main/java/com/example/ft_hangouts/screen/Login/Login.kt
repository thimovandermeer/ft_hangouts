package com.example.ft_hangouts.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ft_hangouts.R
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.ViewModels.LoginViewModel
import com.example.ft_hangouts.screen.Login.ForgotPasswordComposable
import com.example.ft_hangouts.screen.Login.LoginComposable
import com.example.ft_hangouts.screen.Login.PasswordComposable
import com.example.ft_hangouts.screen.Login.UsernameComposable
import com.example.ft_hangouts.ui.theme.Purple700

@Composable
fun LoginPage(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString(stringResource(R.string.signuphereText)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { navController.navigate(Routes.SignUp.route)},
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        var loginstate : MutableState<LoginViewModel.LoginState> = remember {mutableStateOf(
            LoginViewModel.LoginState.INPROGRESS)}

        Text(text = stringResource(R.string.LoginText), style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        UsernameComposable(username, loginstate)
        PasswordComposable(password, loginstate)
        LoginComposable(navController, loginstate, password,viewModel, username)
        ForgotPasswordComposable(navController)

    }
}