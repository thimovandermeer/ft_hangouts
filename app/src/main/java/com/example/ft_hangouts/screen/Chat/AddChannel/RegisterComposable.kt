package com.example.ft_hangouts.screen.Chat.AddChannel

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ViewModels.AddChannelApiStatus
import com.example.ft_hangouts.ViewModels.AddChannelViewModel
import com.example.ft_hangouts.ViewModels.PartnerInfo
import kotlinx.coroutines.launch

@Composable
fun registerChannelComposable(
    viewmodel: AddChannelViewModel,
    state: MutableState<AddChannelApiStatus>,
    chatPartner: PartnerInfo,
) {
    val TAG = "REG Channel Comp"
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            onClick = {
                coroutineScope.launch {
                    viewmodel.savePersonInfo(chatPartner)
                    state.value = viewmodel.addChat(chatPartner.firstName)
                    Log.d(TAG, "state value = ${state.value}")
                }
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = stringResource(R.string.registerchannelText))
        }
    }
}