package com.example.ft_hangouts
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ft_hangouts.screen.ScreenMain
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Main Activity", "Kom ik hier?")
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("Main Activity", "set content")
            Ft_hangoutsTheme {
                Log.d("Main Activity", "theme setting")
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Log.d("Main activity", "Kom ik voor main activity")
                    ScreenMain()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ft_hangoutsTheme {
        Log.d("Main Activity", "default preview")
        ScreenMain()
    }
}
