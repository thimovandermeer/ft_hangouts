package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.ViewModels.AddChannelApiStatus
import com.example.ft_hangouts.ViewModels.AddChannelViewModel
import com.example.ft_hangouts.ViewModels.ChannelListViewModel
import kotlinx.coroutines.launch

@Composable
fun AddChannel(
    navController: NavHostController,
    viewmodel: AddChannelViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val channelname = remember { mutableStateOf(TextFieldValue()) }
    val state : MutableState<AddChannelApiStatus> = remember {mutableStateOf(AddChannelApiStatus.INPROGRESS)}
    val TAG = "add channel"
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.value == AddChannelApiStatus.DONE) {
            Log.d("Add channel view", "channelstate is done")
            navController.navigate(Routes.Chat.route)
            state.value = AddChannelApiStatus.INPROGRESS
        } else if (state.value == AddChannelApiStatus.ERROR) {
            Log.d("Add channel view", "channelstate is done")
            Text(text = "Channel already exists")
        }
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create new chat",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(

                label = { Text(text = "chat partner") },
                value = channelname.value,
                onValueChange = { channelname.value = it })
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                                state.value = viewmodel.addChat(channelname.value.text)
                                Log.d(TAG, "state value = ${state.value}")
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
    }

}