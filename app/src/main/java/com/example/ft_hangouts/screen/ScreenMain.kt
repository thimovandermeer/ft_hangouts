package com.example.ft_hangouts.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.screen.Chat.AddChannel
import com.example.ft_hangouts.screen.Chat.ChatPage
import com.example.ft_hangouts.screen.Chat.MessageListScreen


@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }

        composable(Routes.SignUp.route) {
            SignUp(navController = navController)
        }

        composable(Routes.Chat.route) {
            ChatPage(navController = navController)
        }

        composable(Routes.ForgotPassword.route) { navBackStack ->
            ForgotPassword(navController = navController)
        }

        composable(Routes.AddChannel.route) { navBackStack ->
            AddChannel(navController = navController)
        }

        composable("MessageList/{uid}") { backStackEntry ->
            MessageListScreen(
                navController = navController,
                uuid = backStackEntry.arguments?.getString("uid"))
        }
    }
}