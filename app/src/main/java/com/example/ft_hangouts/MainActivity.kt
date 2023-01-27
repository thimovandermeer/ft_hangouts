package com.example.ft_hangouts
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ft_hangouts.screen.ScreenMain
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object CurrentUser {
        lateinit var username: String
        @RequiresApi(Build.VERSION_CODES.O)
        var timeLeftApp: LocalDateTime = LocalDateTime.MAX
    }
    private val viewModel by viewModels<RegisterViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ft_hangoutsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenMain()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ft_hangoutsTheme {
        ScreenMain()
    }
}
