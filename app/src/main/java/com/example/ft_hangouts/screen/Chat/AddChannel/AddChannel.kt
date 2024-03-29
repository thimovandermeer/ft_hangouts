package com.example.ft_hangouts.screen.Chat

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ft_hangouts.R
import com.example.ft_hangouts.Routes
import com.example.ft_hangouts.ViewModels.AddChannelApiStatus
import com.example.ft_hangouts.ViewModels.AddChannelViewModel
import com.example.ft_hangouts.screen.Chat.AddChannel.AddChatPartnerInfo


@Composable
fun AddChannel(
    navController: NavHostController,
    viewmodel: AddChannelViewModel = hiltViewModel(),
) {
    val state : MutableState<AddChannelApiStatus> = remember {mutableStateOf(AddChannelApiStatus.INPROGRESS)}
    val TAG = "add channel"
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.value == AddChannelApiStatus.DONE) {
            Log.d(TAG, "channelstate is done")
            navController.navigate(Routes.Chat.route)
            state.value = AddChannelApiStatus.INPROGRESS
        } else if (state.value == AddChannelApiStatus.ERROR) {
            Log.d(TAG, "channelstate is done")
            Text(text = stringResource(R.string.channelalreadtexistsText))
        }
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.createnewchatText),
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            AddChatPartnerInfo(viewmodel, state)

        }
    }

}