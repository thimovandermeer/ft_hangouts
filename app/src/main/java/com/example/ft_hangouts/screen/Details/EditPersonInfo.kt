package com.example.ft_hangouts.screen.Details

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ViewModels.AddChannelViewModel
import com.example.ft_hangouts.ViewModels.DetailsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@Composable
fun EditPersonInfo(
    person: String,
    navController: NavController,
    addChannelInputViewmodel: AddChannelViewModel = hiltViewModel()
) {
    Log.d("Edit person info", person)
    Text(text = stringResource(R.string.changingpersoninfoText))
    Spacer(modifier = Modifier.height(20.dp))
    val coroutineScope = rememberCoroutineScope()
    val firstname = remember { mutableStateOf(TextFieldValue()) }
    val lastname = remember { mutableStateOf(TextFieldValue()) }
    val profession = remember { mutableStateOf(TextFieldValue()) }
    val favoritebeer = remember { mutableStateOf(TextFieldValue()) }
    val favoriteanimal = remember { mutableStateOf(TextFieldValue()) }

    fun editPersonInfo() {
        coroutineScope.launch {
            addChannelInputViewmodel.editPersonInfo(
                person,
                firstname.value.text,
                lastname.value.text,
                profession.value.text,
                favoritebeer.value.text,
                favoriteanimal.value.text)
        }
        navController.navigate("Details/${firstname.value.text}")
    }

    Column(Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(R.string.firstnameText)) },
            value = firstname.value,
            onValueChange = { firstname.value = it }
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            label = { Text(text = stringResource(R.string.lastnameText)) },
            value = lastname.value,
            onValueChange = { lastname.value = it }
        )
        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            label = { Text(text = stringResource(R.string.professionText)) },
            value = profession.value,
            onValueChange = { profession.value = it }
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            label = { Text(text = stringResource(R.string.epicbeerText)) },
            value = favoritebeer.value,
            onValueChange = { favoritebeer.value = it }
        )

        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            label = { Text(text = stringResource(R.string.epicbeerText)) },
            value = favoritebeer.value,
            onValueChange = { favoritebeer.value = it }
        )
        
        Button(
            onClick = {
                editPersonInfo()
            }
        ) {
            Text(text = stringResource(R.string.submitchangesText))
        }

    }


}