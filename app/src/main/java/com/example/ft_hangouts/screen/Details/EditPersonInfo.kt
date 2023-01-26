package com.example.ft_hangouts.screen.Details

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R

@Composable
fun EditPersonInfo(person: String) {
    Log.d("Edit person info", person)
    Text(text = stringResource(R.string.changingpersoninfoText))
    Spacer(modifier = Modifier.height(20.dp))

    Column(Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        val firstname = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            label = { Text(text = stringResource(R.string.firstnameText)) },
            value = firstname.value,
            onValueChange = { firstname.value = it }
        )
        Spacer(modifier = Modifier.height(30.dp))
        val lastname = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            label = { Text(text = stringResource(R.string.lastnameText)) },
            value = lastname.value,
            onValueChange = { lastname.value = it }
        )
        Spacer(modifier = Modifier.height(30.dp))

        val profession = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            label = { Text(text = stringResource(R.string.professionText)) },
            value = profession.value,
            onValueChange = { profession.value = it }
        )
        Spacer(modifier = Modifier.height(30.dp))
        val favoritebeer = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            label = { Text(text = stringResource(R.string.epicbeerText)) },
            value = favoritebeer.value,
            onValueChange = { favoritebeer.value = it }
        )


    }

}