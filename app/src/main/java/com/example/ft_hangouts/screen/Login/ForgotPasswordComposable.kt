package com.example.ft_hangouts.screen.Login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ft_hangouts.R
import com.example.ft_hangouts.Routes

@Composable
fun ForgotPasswordComposable(navController: NavHostController) {
    Spacer(modifier = Modifier.height(20.dp))
    ClickableText(
        text = AnnotatedString(stringResource(R.string.forgotpasswordText)),
        onClick = {navController.navigate(Routes.ForgotPassword.route) },
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Default
        )
    )
}