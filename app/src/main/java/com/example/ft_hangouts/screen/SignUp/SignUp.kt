package com.example.ft_hangouts.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ft_hangouts.RegisterViewModel
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.screen.SignUp.Emailcomposable
import com.example.ft_hangouts.screen.SignUp.Passwordcomposable
import com.example.ft_hangouts.screen.SignUp.Registercomposable
import com.example.ft_hangouts.screen.SignUp.Usernamecomposeable

@Composable
fun SignUp(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Inputfields(navController)
    }
}

@Composable
fun Inputfields(navController: NavHostController, viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val email = remember {mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val accept : MutableState<RegisterViewModel.state> = remember {mutableStateOf(RegisterViewModel.state.INPROGRESS)}
        Text(text = "Register", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))

        Usernamecomposeable(accept, username)
        Emailcomposable(accept, email)
        Passwordcomposable(accept, password)
        Registercomposable(navController,accept, viewModel, username, password, email)
        Log.d("Inputfields", "accept.value = ${accept.value}")
        if (accept.value == RegisterViewModel.state.SUCCESS) {
            Log.d("Inputfields", "Lets go")
            navController.navigate(Routes.Login.route)
        }


    }
}