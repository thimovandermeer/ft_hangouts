package com.example.ft_hangouts.screen

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ft_hangouts.MainActivity
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.screen.Chat.AddChannel
import com.example.ft_hangouts.screen.Chat.ChatPage
import com.example.ft_hangouts.screen.Chat.MessageListScreen
import com.example.ft_hangouts.screen.Details.DetailsScreen
import com.example.ft_hangouts.screen.Details.EditPersonInfo
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMain(){
    val navController = rememberNavController()
    val activity = (LocalContext.current as? Activity)
    BackHandler(
        enabled = true,
    ) {
        Log.d("Screenmain", "Ik kom hier nu niet meer in he")
        MainActivity.CurrentUser.timeLeftApp = LocalDateTime.now()
        activity?.finish()
    }

    if (MainActivity.timeLeftApp != LocalDateTime.MAX) {
        Text(text = "The time we closed the app ${MainActivity.CurrentUser.timeLeftApp}")
    }
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
