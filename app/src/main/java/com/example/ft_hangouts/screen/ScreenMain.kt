package com.example.ft_hangouts.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.screen.Chat.AddChannel
import com.example.ft_hangouts.screen.Chat.ChatPage
import com.example.ft_hangouts.screen.Chat.MessageListScreen
import com.example.ft_hangouts.screen.Details.DetailsScreen
import com.example.ft_hangouts.screen.Details.EditPersonInfo


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
                uuid = backStackEntry.arguments?.getString("uid")
            )
        }

        composable("Details/{first_person}") { backStackEntry ->
            DetailsScreen(
                navController = navController,
                person = backStackEntry.arguments?.getString("first_person")
            )
        }

        composable("EditPersonInfo/{person}") { backStackEntry ->
            backStackEntry.arguments?.getString("person")?.let {
                EditPersonInfo(
                    person = it,
                    navController = navController
                )
            }
        }
        }
    }
