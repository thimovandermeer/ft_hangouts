package com.example.ft_hangouts.screen.Chat.AddChannel

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.ViewModels.AddChannelApiStatus
import com.example.ft_hangouts.ViewModels.AddChannelViewModel
import com.example.ft_hangouts.ViewModels.PartnerInfo

@Composable
fun AddChatPartnerInfo(
    viewmodel: AddChannelViewModel,
    state: MutableState<AddChannelApiStatus>,
) {
    val chatPartner by viewmodel.uiState.collectAsState()
    val firstname = remember { mutableStateOf(TextFieldValue()) }
    val lastname = remember { mutableStateOf(TextFieldValue()) }
    val profession = remember { mutableStateOf(TextFieldValue()) }
    val favoriteAnimal = remember { mutableStateOf(TextFieldValue()) }
    val epicBeer = remember { mutableStateOf(TextFieldValue()) }

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        label = { Text(text = "firstname") },
        value = firstname.value,
        onValueChange = { firstname.value = it })
    Spacer(modifier = Modifier.height(20.dp))
    TextField(

        label = { Text(text = "lastname") },
        value = lastname.value,
        onValueChange = { lastname.value = it })
    Spacer(modifier = Modifier.height(20.dp))

    Spacer(modifier = Modifier.height(20.dp))
    TextField(

        label = { Text(text = "profession") },
        value = profession.value,
        onValueChange = { profession.value = it })
    Spacer(modifier = Modifier.height(20.dp))

    Spacer(modifier = Modifier.height(20.dp))
    TextField(

        label = { Text(text = "favorite animal") },
        value = favoriteAnimal.value,
        onValueChange = { favoriteAnimal.value = it })
    Spacer(modifier = Modifier.height(20.dp))

    Spacer(modifier = Modifier.height(20.dp))
    TextField(

        label = { Text(text = "epic beer") },
        value = epicBeer.value,
        onValueChange = { epicBeer.value = it })
    Spacer(modifier = Modifier.height(20.dp))
    val addPartner: PartnerInfo = PartnerInfo(firstname.component1().text, lastname.component1().text, profession.component1().text, favoriteAnimal.component1().text, epicBeer.component1().text)
    registerChannelComposable(viewmodel, state, addPartner)

}